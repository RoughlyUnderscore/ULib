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
 * A convenience class for handling time.
 */
@Serializable
data class Time(
  val amount: Long,
  val unit: TimeMeasurementUnit
) {
  constructor(amount: Int, unit: TimeMeasurementUnit) : this(amount.toLong(), unit)

  companion object {
    /**
     * A constant for zero time.
     */
    val ZERO = Time(0, TimeMeasurementUnit.MILLISECONDS)

    /**
     * A constant for one millisecond.
     */
    val ONE_MILLISECOND = Time(1, TimeMeasurementUnit.MILLISECONDS)

    /**
     * A constant for one tick.
     */
    val ONE_TICK = Time(1, TimeMeasurementUnit.TICKS)

    /**
     * A constant for one second.
     */
    val ONE_SECOND = Time(1, TimeMeasurementUnit.SECONDS)

    /**
     * A constant for one minute.
     */
    val ONE_MINUTE = Time(1, TimeMeasurementUnit.MINUTES)

    /**
     * A constant for one hour.
     */
    val ONE_HOUR = Time(1, TimeMeasurementUnit.HOURS)

    /**
     * A constant for one day.
     */
    val ONE_DAY = Time(1, TimeMeasurementUnit.DAYS)

    /**
     * A constant for one week.
     */
    val ONE_WEEK = Time(1, TimeMeasurementUnit.WEEKS)

    /**
     * A constant for one month.
     */
    val ONE_MONTH = Time(1, TimeMeasurementUnit.MONTHS)

    /**
     * A constant for one year.
     */
    val ONE_YEAR = Time(1, TimeMeasurementUnit.YEARS)

    /**
     * A constant for one decade.
     */
    val ONE_DECADE = Time(1, TimeMeasurementUnit.DECADES)

    /**
     * A constant for one century.
     */
    val ONE_CENTURY = Time(1, TimeMeasurementUnit.CENTURIES)

    /**
     * A constant for one millennium.
     */
    val ONE_MILLENNIUM = Time(1, TimeMeasurementUnit.MILLENNIA)

    /**
     * A constant for one eon.
     */
    val ONE_EON = Time(1, TimeMeasurementUnit.EONS)

    /**
     * A constant for forever ([Long.MAX_VALUE] milliseconds).
     */
    val FOREVER = Time(Long.MAX_VALUE, TimeMeasurementUnit.FOREVER)

    /**
     * A constant for now ([System.currentTimeMillis] milliseconds).
     */
    val NOW: Time
      get() = Time(System.currentTimeMillis(), TimeMeasurementUnit.MILLISECONDS)
  }

  /**
   * Adds two [Time] objects together.
   */
  operator fun plus(other: Time) = Time(this.amount + other.amount, TimeMeasurementUnit.MILLISECONDS)

  /**
   * Subtracts two [Time] objects.
   */
  operator fun minus(other: Time) = Time(this.amount - other.amount, TimeMeasurementUnit.MILLISECONDS)

  /**
   * Multiplies two [Time] objects.
   */
  operator fun times(other: Time) = Time(this.amount * other.amount, TimeMeasurementUnit.MILLISECONDS)

  /**
   * Divides two [Time] objects.
   */
  operator fun div(other: Time) = Time(this.amount / other.amount, TimeMeasurementUnit.MILLISECONDS)

  /**
   * Returns a representation of this [Time] object in milliseconds.
   */
  val millis get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount
    TimeMeasurementUnit.TICKS -> amount * 50
    TimeMeasurementUnit.SECONDS -> amount * 50 * 20
    TimeMeasurementUnit.MINUTES -> amount * 50 * 20 * 60
    TimeMeasurementUnit.HOURS -> amount * 50 * 20 * 60 * 60
    TimeMeasurementUnit.DAYS -> amount * 50 * 20 * 60 * 60 * 24
    TimeMeasurementUnit.WEEKS -> amount * 50 * 20 * 60 * 60 * 24 * 7
    TimeMeasurementUnit.MONTHS -> amount * 50 * 20 * 60 * 60 * 24 * 30
    TimeMeasurementUnit.YEARS -> amount * 50 * 20 * 60 * 60 * 24 * 365
    TimeMeasurementUnit.DECADES -> amount * 50 * 20 * 60 * 60 * 24 * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 50 * 20 * 60 * 60 * 24 * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 50 * 20 * 60 * 60 * 24 * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 50 * 20 * 60 * 60 * 24 * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in ticks.
   */
  val ticks get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50
    TimeMeasurementUnit.TICKS -> amount
    TimeMeasurementUnit.SECONDS -> amount * 20
    TimeMeasurementUnit.MINUTES -> amount * 20 * 60
    TimeMeasurementUnit.HOURS -> amount * 20 * 60 * 60
    TimeMeasurementUnit.DAYS -> amount * 20 * 60 * 60 * 24
    TimeMeasurementUnit.WEEKS -> amount * 20 * 60 * 60 * 24 * 7
    TimeMeasurementUnit.MONTHS -> amount * 20 * 60 * 60 * 24 * 30
    TimeMeasurementUnit.YEARS -> amount * 20 * 60 * 60 * 24 * 365
    TimeMeasurementUnit.DECADES -> amount * 20 * 60 * 60 * 24 * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 20 * 60 * 60 * 24 * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 20 * 60 * 60 * 24 * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 20 * 60 * 60 * 24 * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in seconds.
   */
  val seconds get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20
    TimeMeasurementUnit.TICKS -> amount / 20
    TimeMeasurementUnit.SECONDS -> amount
    TimeMeasurementUnit.MINUTES -> amount * 60
    TimeMeasurementUnit.HOURS -> amount * 60 * 60
    TimeMeasurementUnit.DAYS -> amount * 60 * 60 * 24
    TimeMeasurementUnit.WEEKS -> amount * 60 * 60 * 24 * 7
    TimeMeasurementUnit.MONTHS -> amount * 60 * 60 * 24 * 30
    TimeMeasurementUnit.YEARS -> amount * 60 * 60 * 24 * 365
    TimeMeasurementUnit.DECADES -> amount * 60 * 60 * 24 * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 60 * 60 * 24 * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 60 * 60 * 24 * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 60 * 60 * 24 * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in minutes.
   */
  val minutes get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60
    TimeMeasurementUnit.TICKS -> amount / 20 / 60
    TimeMeasurementUnit.SECONDS -> amount / 60
    TimeMeasurementUnit.MINUTES -> amount
    TimeMeasurementUnit.HOURS -> amount * 60
    TimeMeasurementUnit.DAYS -> amount * 60 * 24
    TimeMeasurementUnit.WEEKS -> amount * 60 * 24 * 7
    TimeMeasurementUnit.MONTHS -> amount * 60 * 24 * 30
    TimeMeasurementUnit.YEARS -> amount * 60 * 24 * 365
    TimeMeasurementUnit.DECADES -> amount * 60 * 24 * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 60 * 24 * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 60 * 24 * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 60 * 24 * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in hours.
   */
  val hours get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 * 60
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60
    TimeMeasurementUnit.MINUTES -> amount / 60
    TimeMeasurementUnit.HOURS -> amount
    TimeMeasurementUnit.DAYS -> amount * 24
    TimeMeasurementUnit.WEEKS -> amount * 24 * 7
    TimeMeasurementUnit.MONTHS -> amount * 24 * 30
    TimeMeasurementUnit.YEARS -> amount * 24 * 365
    TimeMeasurementUnit.DECADES -> amount * 24 * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 24 * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 24 * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 24 * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in days.
   */
  val days get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24
    TimeMeasurementUnit.HOURS -> amount / 24
    TimeMeasurementUnit.DAYS -> amount
    TimeMeasurementUnit.WEEKS -> amount * 7
    TimeMeasurementUnit.MONTHS -> amount * 30
    TimeMeasurementUnit.YEARS -> amount * 365
    TimeMeasurementUnit.DECADES -> amount * 365 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 365 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 365 * 1000
    TimeMeasurementUnit.EONS -> amount * 365 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in weeks.
   */
  val weeks get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 7
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 7
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 7
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 7
    TimeMeasurementUnit.HOURS -> amount / 24 / 7
    TimeMeasurementUnit.DAYS -> amount / 7
    TimeMeasurementUnit.WEEKS -> amount
    TimeMeasurementUnit.MONTHS -> amount * 4
    TimeMeasurementUnit.YEARS -> amount * 52
    TimeMeasurementUnit.DECADES -> amount * 52 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 52 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 52 * 1000
    TimeMeasurementUnit.EONS -> amount * 52 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in months.
   */
  val months get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 30
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 30
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 30
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 30
    TimeMeasurementUnit.HOURS -> amount / 24 / 30
    TimeMeasurementUnit.DAYS -> amount / 30
    TimeMeasurementUnit.WEEKS -> amount / 4
    TimeMeasurementUnit.MONTHS -> amount
    TimeMeasurementUnit.YEARS -> amount * 12
    TimeMeasurementUnit.DECADES -> amount * 12 * 10
    TimeMeasurementUnit.CENTURIES -> amount * 12 * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 12 * 1000
    TimeMeasurementUnit.EONS -> amount * 12 * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in years.
   */
  val years get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 365
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 365
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 365
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 365
    TimeMeasurementUnit.HOURS -> amount / 24 / 365
    TimeMeasurementUnit.DAYS -> amount / 365
    TimeMeasurementUnit.WEEKS -> amount / 52
    TimeMeasurementUnit.MONTHS -> amount / 12
    TimeMeasurementUnit.YEARS -> amount
    TimeMeasurementUnit.DECADES -> amount * 10
    TimeMeasurementUnit.CENTURIES -> amount * 100
    TimeMeasurementUnit.MILLENNIA -> amount * 1000
    TimeMeasurementUnit.EONS -> amount * 10000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in decades.
   */
  val decades get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount * 50 * 20 / 60 / 60 / 24 / 365 / 10
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 365 / 10
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 365 / 10
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 365 / 10
    TimeMeasurementUnit.HOURS -> amount / 24 / 365 / 10
    TimeMeasurementUnit.DAYS -> amount / 365 / 10
    TimeMeasurementUnit.WEEKS -> amount / 52 / 10
    TimeMeasurementUnit.MONTHS -> amount / 12 / 10
    TimeMeasurementUnit.YEARS -> amount / 10
    TimeMeasurementUnit.DECADES -> amount
    TimeMeasurementUnit.CENTURIES -> amount * 10
    TimeMeasurementUnit.MILLENNIA -> amount * 100
    TimeMeasurementUnit.EONS -> amount * 1000
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in centuries.
   */
  val centuries get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 365 / 100
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 365 / 100
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 365 / 100
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 365 / 100
    TimeMeasurementUnit.HOURS -> amount / 24 / 365 / 100
    TimeMeasurementUnit.DAYS -> amount / 365 / 100
    TimeMeasurementUnit.WEEKS -> amount / 52 / 100
    TimeMeasurementUnit.MONTHS -> amount / 12 / 100
    TimeMeasurementUnit.YEARS -> amount / 100
    TimeMeasurementUnit.DECADES -> amount / 10
    TimeMeasurementUnit.CENTURIES -> amount
    TimeMeasurementUnit.MILLENNIA -> amount * 10
    TimeMeasurementUnit.EONS -> amount * 100
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }
  /**
   * Returns a representation of this [Time] object in millennia.
   */
  val millennia get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 365 / 1000
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 365 / 1000
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 365 / 1000
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 365 / 1000
    TimeMeasurementUnit.HOURS -> amount / 24 / 365 / 1000
    TimeMeasurementUnit.DAYS -> amount / 365 / 1000
    TimeMeasurementUnit.WEEKS -> amount / 52 / 1000
    TimeMeasurementUnit.MONTHS -> amount / 12 / 1000
    TimeMeasurementUnit.YEARS -> amount / 1000
    TimeMeasurementUnit.DECADES -> amount / 100
    TimeMeasurementUnit.CENTURIES -> amount / 10
    TimeMeasurementUnit.MILLENNIA -> amount
    TimeMeasurementUnit.EONS -> amount * 10
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }

  /**
   * Returns a representation of this [Time] object in eons.
   */
  val eons get() = when (unit) {
    TimeMeasurementUnit.MILLISECONDS -> amount / 50 / 20 / 60 / 60 / 24 / 365 / 10000
    TimeMeasurementUnit.TICKS -> amount / 20 / 60 / 60 / 24 / 365 / 10000
    TimeMeasurementUnit.SECONDS -> amount / 60 / 60 / 24 / 365 / 10000
    TimeMeasurementUnit.MINUTES -> amount / 60 / 24 / 365 / 10000
    TimeMeasurementUnit.HOURS -> amount / 24 / 365 / 10000
    TimeMeasurementUnit.DAYS -> amount / 365 / 10000
    TimeMeasurementUnit.WEEKS -> amount / 52 / 10000
    TimeMeasurementUnit.MONTHS -> amount / 12 / 10000
    TimeMeasurementUnit.YEARS -> amount / 10000
    TimeMeasurementUnit.DECADES -> amount / 1000
    TimeMeasurementUnit.CENTURIES -> amount / 100
    TimeMeasurementUnit.MILLENNIA -> amount / 10
    TimeMeasurementUnit.EONS -> amount
    TimeMeasurementUnit.FOREVER -> Long.MAX_VALUE
  }
}

/**
 * The unit of measurement for a [Time] object.
 */
@Serializable
enum class TimeMeasurementUnit {
  MILLISECONDS,
  TICKS,
  SECONDS, MINUTES, HOURS,
  DAYS, WEEKS, MONTHS,
  YEARS, DECADES, CENTURIES, MILLENNIA, EONS,
  FOREVER
}