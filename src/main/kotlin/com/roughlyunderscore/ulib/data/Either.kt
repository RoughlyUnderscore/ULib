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

import kotlinx.serialization.Serializable

/**
 * A simple Either implementation. Serves the purpose of a double-ended variable type,
 * where the value can be either of two types. This is useful for error handling, where
 * the right value can be an error, and the left value can be the result.
 * [L] is the left value, [R] is the right value.
 */

class Either<out L, out R> private constructor(private val left: L?, private val right: R?) {
  /**
   * Returns true if the value is left (not null), false otherwise.
   */
  val isLeft: Boolean get() = left != null

  /**
   * Returns true if the value is right (not null), false otherwise.
   */
  val isRight: Boolean get() = right != null

  /**
   * Returns the left value with a strict guarantee that it is not null.
   * Throws a [NullPointerException] if the value is null.
   */
  fun left(): L = left!!

  /**
   * Returns the right value with a strict guarantee that it is not null.
   * Throws a [NullPointerException] if the value is null.
   */
  fun right(): R = right!!

  /**
   * Unwraps the left value, or throws a [NoSuchElementException] if the value is null.
   */
  fun unwrapLeft(): L = left ?: throw NoSuchElementException("No left value present")

  /**
   * Unwraps the right value, or throws a [NoSuchElementException] if the value is null.
   */
  fun unwrapRight(): R = right ?: throw NoSuchElementException("No right value present")

  /**
   * Folds the value into a single value, depending on whether it is left or right. The return
   * is provided by two suppliers [onLeft] and [onRight], which are called depending on the value
   * of the Either.
   */
  fun <K> fold(onLeft: (L) -> K, onRight: (R) -> K): K = if (isLeft) onLeft(left()) else onRight(right())

  /**
   * Folds the value into a single value, depending on whether it is left or right. The return
   * is provided by two suppliers [onLeft] and [onRight], which are called depending on the value
   * of the Either. The suppliers are called in a suspend context.
   */
  suspend fun <K> foldSuspend(onLeft: suspend (L) -> K, onRight: suspend (R) -> K): K = if (isLeft) onLeft(left()) else onRight(right())

  /**
   * Maps the left value to a new value, or returns the right value if the value is right.
   * The return is provided by the supplier [transform], which is called if the value is left.
   */
  fun <K> mapLeft(transform: (L) -> K): Either<K, R> = if (isLeft) left(transform(left())) else right(right!!)

  /**
   * Maps the right value to a new value, or returns the left value if the value is left.
   * The return is provided by the supplier [transform], which is called if the value is right.
   */
  fun <K> mapRight(transform: (R) -> K): Either<L, K> = if (isRight) right(transform(right())) else left(left!!)

  companion object {
    /**
     * Creates a new left value.
     */
    fun <L> left(value: L) = Either(value, null)

    /**
     * Creates a new right value.
     */
    fun <R> right(value: R) = Either(null, value)

    /**
     * Creates a new instance of Either from two nullable values. If both values are non-null,
     * or both values are null, an [IllegalArgumentException] is thrown.
     */
    fun <L, R> fromNullable(left: L?, right: R?): Either<L, R> {
      if (left != null && right != null) throw IllegalArgumentException("Both values cannot be non-null")
      if (left == null && right == null) throw IllegalArgumentException("Both values cannot be null")

      return if (left != null) left(left) else right(right!!)
    }

    /**
     * Creates a new instance of Either from two suppliers of nullable values. If both values are non-null,
     * or both values are null, an [IllegalArgumentException] is thrown.
     */
    fun <L, R> fromNullable(left: () -> L?, right: () -> R?): Either<L, R> = fromNullable(left(), right())

    /**
     * Immediately folds the value into a single value, depending on whether it is left or right. The return
     * is provided by two suppliers [onLeft] and [onRight], which are called depending on which
     * value is non-null. This does not create a new instance of Either.
     */
    fun <L, R, K> immediatelyFold(left: L?, right: R?, onLeft: (L) -> K, onRight: (R) -> K): K {
      return if (left != null) onLeft(left) else onRight(right!!)
    }
  }
}