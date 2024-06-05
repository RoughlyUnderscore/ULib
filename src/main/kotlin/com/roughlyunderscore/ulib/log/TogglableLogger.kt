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

package com.roughlyunderscore.ulib.log

import java.util.logging.Logger

/**
 * A toggleable logger that can be enabled or disabled at any point.
 * @param logger The logger to be used.
 * @param enabled Whether the logger is enabled or not.
 */
class TogglableLogger(private val logger: Logger, var enabled: Boolean = true) {
  /**
   * Executes a [block] on the [logger] if it is [enabled].
   */
  fun commit(block: Logger.() -> Unit) {
    if (enabled) logger.block()
  }
}