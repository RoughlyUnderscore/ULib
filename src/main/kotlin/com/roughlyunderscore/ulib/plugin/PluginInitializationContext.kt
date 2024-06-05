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

import kotlinx.serialization.Serializable

@Serializable
class PluginInitializationContext {
  val folders = mutableListOf<String>()
  val files = mutableListOf<String>()

  class Builder {
    private val context = PluginInitializationContext()

    /**
     * Creates folders in the data folder of the plugin if not present.
     */
    fun requireFolders(vararg folders: String) = also { context.folders.addAll(folders) }

    /**
     * Loads files from the plugin's `resources` folder if not present.
     * Use `/` as a path separator.
     */
    fun requireFilesAsResources(vararg names: String) = also { context.files.addAll(names) }

    /**
     * Builds the context.
     */
    fun build() = context
  }
}