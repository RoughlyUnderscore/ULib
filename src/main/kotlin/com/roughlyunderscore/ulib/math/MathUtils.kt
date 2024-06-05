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

package com.roughlyunderscore.ulib.math

/**
 * Clamps a value (double) between two other doubles [min] and [max].
 * @return the resulting double
 */
fun Double.clamp(min: Double, max: Double): Double {
  if (this < min) return min
  if (this > max) return max
  return this
}

/**
 * Clamps a value (int) between two other ints [min] and [max].
 * @return the resulting int
 */
infix fun Int.clamp(range: IntRange): Int {
  if (this < range.first) return range.first
  if (this > range.last) return range.last
  return this
}