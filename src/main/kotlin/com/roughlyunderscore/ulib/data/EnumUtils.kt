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

package com.roughlyunderscore.ulib.data

import org.jetbrains.annotations.Contract
import java.lang.IllegalArgumentException

/**
 * Tries to look up [value] in an enum, returns null if not found.
 */
inline fun <reified T : Enum<T>> safeValue(value: String?): T? {
  if (value == null) return null
  return try {
    enumValueOf<T>(value)
  } catch (ex: IllegalArgumentException) {
    null
  }
}

/**
 * Tries to look up [value] in an enum, returns [fallback] if not found.
 */
@Contract("_, !null -> !null")
inline fun <reified T : Enum<T>> safeValueOr(value: String?, fallback: T?): T? {
  return safeValue<T>(value) ?: fallback
}