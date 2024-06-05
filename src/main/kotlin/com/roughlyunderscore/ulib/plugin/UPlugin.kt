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

package com.roughlyunderscore.ulib.plugin

import com.roughlyunderscore.ulib.io.requireChildDirectory
import com.roughlyunderscore.ulib.io.requireFileAsResource
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

open class UPlugin(private val context: PluginInitializationContext) : JavaPlugin() {
  /**
   * This code will be run before any UPlugin's default load
   * operations commence.
   */
  open fun beforeLoad() {}

  /**
   * This code will be run after any UPlugin's default load
   * operations finish.
   */
  open fun afterLoad() {}

  /**
   * This code will be run before any UPlugin's default startup
   * operations commence (e.g. creating default files).
   */
  open fun beforeStartup() {}

  /**
   * This code will be run after any UPlugin's default startup
   * operations finish (e.g. creating default files).
   */
  open fun afterStartup() {}

  /**
   * This code will be run before any UPlugin's default shutdown
   * operations commence.
   */
  open fun beforeDisable() {}

  /**
   * This code will be run after any UPlugin's default shutdown
   * operations finish.
   */
  open fun afterDisable() {}

  override fun onLoad() {
    beforeLoad()
    afterLoad()
  }

  override fun onEnable() {
    beforeStartup()

    context.folders.forEach { dataFolder.requireChildDirectory(it) }
    context.files.forEach {
      val (name, path) = it.split("/").let { split ->
        return@let split.last() to split.dropLast(1).let { prepath -> prepath.ifEmpty { null } }
      }

      var fullPath = dataFolder
      path?.forEach { element -> fullPath = fullPath.resolve(element) }

      fullPath.resolve(name).requireFileAsResource(path?.joinToString(File.separator), this)
    }

    afterStartup()
  }

  override fun onDisable() {
    beforeDisable()
    afterDisable()
  }
}