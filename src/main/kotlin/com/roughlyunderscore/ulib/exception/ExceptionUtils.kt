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

package com.roughlyunderscore.ulib.exception

object ExceptionUtils {
  /**
   * Tries to execute [block], if it throws an exception of type [exceptionClass], [ifThrows] is called with the exception.
   */
  fun ifThrows(exceptionClass: Class<out Exception>? = Exception::class.java, block: () -> Any, ifThrows: (Exception) -> Any) {
    try {
      block()
    } catch (ex: Exception) {
      if (ex::class.java.isInstance(exceptionClass)) ifThrows(ex)
    }
  }
}