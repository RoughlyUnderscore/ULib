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

import com.roughlyunderscore.ulib.data.Time
import com.roughlyunderscore.ulib.data.TimeMeasurementUnit
import kotlin.math.pow

object NumberUtils {
  /**
   * Generates a list of integers from [start] to [end] with a [step].
   */
  fun generateIntList(start: Int = 0, end: Int = 0, step: Int = 1): List<Int> {
    val list = mutableListOf<Int>()
    for (i in start .. end step step) {
      list.add(i)
    }
    return list
  }
}

val Int.ten: Int get() = this * 10
val Int.hundred: Int get() = this * 100
val Int.thousand: Int get() = this * 1000
val Int.million: Int get() = this.thousand.thousand

/**
 * Will most likely overflow.
 */
val Int.billion: Int get() = this.million.thousand

val Long.ten: Long get() = this * 10
val Long.hundred: Long get() = this * 100
val Long.thousand: Long get() = this * 1000
val Long.million: Long get() = this.thousand.thousand
val Long.billion: Long get() = this.million.thousand
val Long.trillion: Long get() = this.billion.thousand
val Long.quadrillion: Long get() = this.trillion.thousand

/**
 * Will most likely overflow.
 */
val Long.quintillion: Long get() = this.quadrillion * 1000

val Double.ten: Double get() = this * 10
val Double.hundred: Double get() = this * 100
val Double.thousand: Double get() = this * 1000
val Double.million: Double get() = this * 1E6
val Double.billion: Double get() = this * 1E9
val Double.trillion: Double get() = this * 1E12
val Double.quadrillion: Double get() = this * 1E15
val Double.quintillion: Double get() = this * 1E18
val Double.sextillion: Double get() = this * 1E21
val Double.septillion: Double get() = this * 1E24
val Double.octillion: Double get() = this * 1E27
val Double.nonillion: Double get() = this * 1E30
val Double.decillion: Double get() = this * 1E33
val Double.undecillion: Double get() = this * 1E36
val Double.duodecillion: Double get() = this * 1E39
val Double.tredecillion: Double get() = this * 1E42
val Double.quattuordecillion: Double get() = this * 1E45
val Double.quindecillion: Double get() = this * 1E48
val Double.sexdecillion: Double get() = this * 1E51
val Double.septendecillion: Double get() = this * 1E54
val Double.octodecillion: Double get() = this * 1E57
val Double.novemdecillion: Double get() = this * 1E60
val Double.vigintillion: Double get() = this * 1E63
val Double.unvigintillion: Double get() = this * 1E66
val Double.duovigintillion: Double get() = this * 1E69
val Double.tresvigintillion: Double get() = this * 1E72
val Double.quattuorvigintillion: Double get() = this * 1E75
val Double.quinvigintillion: Double get() = this * 1E78
val Double.sexvigintillion: Double get() = this * 1E81
val Double.septenvigintillion: Double get() = this * 1E84
val Double.octovigintillion: Double get() = this * 1E87
val Double.novemvigintillion: Double get() = this * 1E90
val Double.trigintillion: Double get() = this * 1E93
val Double.untrigintillion: Double get() = this * 1E96
val Double.duotrigintillion: Double get() = this * 1E99
val Double.googol: Double get() = this * 1E100

/**
 * Likely to overflow.
 */
val Double.centillion: Double get() = this * 1E303

/**
 * WILL overflow. Why would you use this?
 * Calling [isInfinite] on this value will return true
 * even when called on [Double.MIN_VALUE]. This is for
 * shits and giggles.
 */
val Double.googolplex: Double get() = this * 10.0.pow(10.0.googol)

val Int.millis get() = Time(this, TimeMeasurementUnit.MILLISECONDS)
val Int.ticks get() = Time(this, TimeMeasurementUnit.TICKS)
val Int.seconds get() = Time(this, TimeMeasurementUnit.SECONDS)
val Int.minutes get() = Time(this, TimeMeasurementUnit.MINUTES)
val Int.hours get() = Time(this, TimeMeasurementUnit.HOURS)
val Int.days get() = Time(this, TimeMeasurementUnit.DAYS)
val Int.weeks get() = Time(this, TimeMeasurementUnit.WEEKS)
val Int.months get() = Time(this, TimeMeasurementUnit.MONTHS)
val Int.years get() = Time(this, TimeMeasurementUnit.YEARS)
val Int.decades get() = Time(this, TimeMeasurementUnit.DECADES)
val Int.centuries get() = Time(this, TimeMeasurementUnit.CENTURIES)
val Int.millennia get() = Time(this, TimeMeasurementUnit.MILLENNIA)
val Int.eons get() = Time(this, TimeMeasurementUnit.EONS)

val Long.millis get() = Time(this, TimeMeasurementUnit.MILLISECONDS)
val Long.ticks get() = Time(this, TimeMeasurementUnit.TICKS)
val Long.seconds get() = Time(this, TimeMeasurementUnit.SECONDS)
val Long.minutes get() = Time(this, TimeMeasurementUnit.MINUTES)
val Long.hours get() = Time(this, TimeMeasurementUnit.HOURS)
val Long.days get() = Time(this, TimeMeasurementUnit.DAYS)
val Long.weeks get() = Time(this, TimeMeasurementUnit.WEEKS)
val Long.months get() = Time(this, TimeMeasurementUnit.MONTHS)
val Long.years get() = Time(this, TimeMeasurementUnit.YEARS)
val Long.decades get() = Time(this, TimeMeasurementUnit.DECADES)
val Long.centuries get() = Time(this, TimeMeasurementUnit.CENTURIES)
val Long.millennia get() = Time(this, TimeMeasurementUnit.MILLENNIA)
val Long.eons get() = Time(this, TimeMeasurementUnit.EONS)