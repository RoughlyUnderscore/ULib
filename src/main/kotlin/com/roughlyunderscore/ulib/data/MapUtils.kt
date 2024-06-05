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

/**
 * Shuffles the values inside a map whilst keeping the keys in order.
 *
 * Example:
 * - `[(0, "a"), (1, "b"), (2, "c"), (3, "4")] -> [(0, "c"), (1, "b"), (2, "d"), (3, "a")]`
 */
fun <A, B> MutableMap<A, B>.shuffleValues(): MutableMap<A, B> {
  val keys = this.keys.toList()
  val shuffledValues = this.values.shuffled()
  for (i in keys.indices) {
    val key = keys[i]
    val value = shuffledValues[i]

    this.replace(key, value)
  }

  return this
}