// Copyright 2024 RoughlyUnderscore
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.roughlyunderscore.ulib.io

import com.roughlyunderscore.ulib.text.endsWithAny
import org.apache.commons.compress.archivers.tar.TarArchiveEntry
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.InputStream
import java.nio.file.Files

/**
 * Untars an input stream into a temporary directory, performs an [action] on it,
 * then deletes the temporary directory. Accepts a filter for file [extensions]
 * (e.g. `listOf("json")`).
 */
fun <R> InputStream.tempUntarOperate(extensions: List<String>, action: (Iterable<File>) -> R): R = TarArchiveInputStream(this).use tarUse@ { tar ->
  val tempFolder = Files.createTempDirectory("temp_untar${System.currentTimeMillis()}").toFile().apply { deleteOnExit() }
  var entry = tar.nextEntry

  while (entry != null) {
    if (!tar.canReadEntryData(entry)) {
      // Wtf?
      entry = tar.nextEntry
      continue
    }

    val file = File(tempFolder, entry.name)

    if (entry.isDirectory) {
      if (!file.isDirectory && !file.mkdirs()) {
        entry = tar.nextEntry
        continue
      }
    } else {
      val parent = file.parentFile
      // Ensure that the parent directory exists, and the entry matches the extension list
      if ((!parent.isDirectory && !parent.mkdirs()) || !entry.name.endsWithAny(*extensions.toTypedArray())) {
        entry = tar.nextEntry
        continue
      }

      file.outputStream().use { output -> IOUtils.copy(tar, output) }
    }

    if (entry.isDirectory) {
      entry = tar.nextEntry
      continue
    }

    entry = tar.nextEntry
  }

  val result = action(FileUtils.listFiles(tempFolder, extensions.toTypedArray(), true))

  // Cleaning up. If this fails, it is not a big deal,
  // as the temp folder will be deleted when the JVM exits,
  // and even if not, the temporary files should be regularly
  // cleaned up by the OS or the user.
  tempFolder.deleteRecursively()

  return@tarUse result
}

/**
 * Untars an input stream into a temporary directory, performs an [action] on it,
 * then retars the directory into an [outFile]. Accepts a filter for file [extensions]
 * (e.g. `listOf("json")`).
 */
fun <R> InputStream.tempUntarOperateRetar(extensions: List<String>, outFile: File, action: (Iterable<File>) -> R): R = this.tempUntarOperate(extensions) { files ->
  val result = action(files)

  outFile.outputStream().use outUse@ { outStream ->
    TarArchiveOutputStream(outStream).use tarUse@{ tarStream ->
      for (file in files) addFileToTarStream(tarStream, file)
      tarStream.finish()
    }
  }

  return@tempUntarOperate result
}

private fun addFileToTarStream(tarStream: TarArchiveOutputStream, file: File, parent: String = "") {
  val tarEntry = TarArchiveEntry(file, parent + file.name)
  tarStream.putArchiveEntry(tarEntry)

  if (file.isFile) {
    file.inputStream().use {
      IOUtils.copy(it, tarStream)
      tarStream.closeArchiveEntry()
    }
  } else {
    tarStream.closeArchiveEntry()
    file.listFiles()?.forEach { addFileToTarStream(tarStream, it, "${tarEntry.name}/") }
  }
}