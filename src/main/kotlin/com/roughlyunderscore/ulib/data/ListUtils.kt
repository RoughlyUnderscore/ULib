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
 * Checks if a collection of [R] contains any item from the other
 * collection of [R].
 */
fun <R> Collection<R>.containsAny(other: Collection<R>): Boolean = other.any { this.contains(it) }

/**
 * Checks if a list of [R] contains all items from the other
 * list of [R].
 */
fun <R> List<R>.isSame(other: List<R>): Boolean {
  if (this.size != other.size) return false
  for (i in this.indices) {
    if (this[i] != other[i]) return false
  }
  return true
}