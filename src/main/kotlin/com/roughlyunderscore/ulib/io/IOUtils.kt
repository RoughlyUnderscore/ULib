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

import net.md_5.bungee.api.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import kotlin.io.path.createDirectory
import kotlin.io.path.isDirectory
import kotlin.io.path.notExists

/**
 * Requires that a child directory exists. If it doesn't, it will be created.
 */
fun File.requireChildDirectory(name: String) {
  val child = this.toPath().resolve(name)
  if (child.notExists() || !child.isDirectory()) child.createDirectory()
}

/**
 * Requires that a file exists in a [childDirectory]. If the child directory doesn't exist, it will be created from the
 * data provided by [plugin]. This is only available if you are running a Spigot server (e.g.
 * will fail on Bungee).
 *
 * For example, if the file is named "config.yml", and the child directory is named "settings", the file will be
 * required to be located at "settings/config.yml". If it isn't, [JavaPlugin.saveResource] will be called with
 * "settings/config.yml" as the resource path.
 * @see JavaPlugin.saveResource
 * @return true if the file already existed, false otherwise
 */
fun File.requireFileAsResource(childDirectory: String?, plugin: JavaPlugin): Boolean {
  if (childDirectory != null) plugin.dataFolder.requireChildDirectory(childDirectory)

  val filePath = this.toPath()
  if (filePath.notExists()) {
    if (childDirectory != null) plugin.saveResource("$childDirectory/$name", true)
    else plugin.saveResource(name, true)
    return false
  }

  return true
}

/**
 * Requires that a file exists in a child directory. If the child directory doesn't exist, it will be created.<br>
 * For example, if the file is named "config.yml", and the child directory is named "settings", the file will be
 * required to be located at "settings/config.yml". If it isn't, the resource will be saved with
 * "settings/config.yml" as the resource path. This is only available if you are running a Bungee server (e.g.
 * will fail on Spigot).
 * @param childDirectory The name of the child directory.
 * @param plugin The plugin to save the resource from.
 */
fun File.requireFileAsResourceBungee(childDirectory: String, plugin: Plugin) {
  plugin.dataFolder.requireChildDirectory(childDirectory)
  val filePath = this.toPath()
  if (filePath.notExists()) {
    plugin.getResourceAsStream("$childDirectory/$name")?.use { inputStream ->
      this.outputStream().use { outputStream ->
        inputStream.copyTo(outputStream)
      }
    }
  }
}

/**
 * Requires that a file exists. If the child directory doesn't exist, it will be created.<br>
 * For example, if the file is named "config.yml", the file will be required to be located at "config.yml".
 * If it isn't, the resource will be saved with "config.yml" as the resource path. Method only available if
 * you are running a Bungee server (e.g. will fail on Spigot).
 * @param plugin The plugin to save the resource from.
 */
fun File.requireFileAsResourceBungee(plugin: Plugin) {
  val filePath = this.toPath()
  if (filePath.notExists()) {
    plugin.getResourceAsStream(name)?.use { inputStream ->
      this.outputStream().use { outputStream ->
        inputStream.copyTo(outputStream)
      }
    }
  }
}