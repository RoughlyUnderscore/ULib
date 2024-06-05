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

package com.roughlyunderscore.ulib.json

import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Performs an action on the first element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyElement(names: Collection<String>, default: () -> R? = { null }, apply: JsonElement.() -> R): R? {
  for (name in names) {
    if (has(name)) return get(name).apply()
  }
  return default()
}

/**
 * Performs an action on the first object with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyObject(names: Collection<String>, default: () -> R? = { null }, apply: JsonObject.() -> R): R? = onAnyElement(names, default) { asJsonObject.apply() }

/**
 * Performs an action on the first string element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyString(names: Collection<String>, default: () -> R? = { null }, apply: String.() -> R): R? = onAnyElement(names, default) { asString?.apply() }

/**
 * Performs an action on the first int element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyInt(names: Collection<String>, default: () -> R? = { null }, apply: Int.() -> R): R? = onAnyElement(names, default) { asInt.apply() }

/**
 * Performs an action on the first boolean element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyBoolean(names: Collection<String>, default: () -> R? = { null }, apply: Boolean.() -> R): R? = onAnyElement(names, default) { asBoolean.apply() }

/**
 * Performs an action on the first double element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyDouble(names: Collection<String>, default: () -> R? = { null }, apply: Double.() -> R): R? = onAnyElement(names, default) { asDouble.apply() }

/**
 * Performs an action on the first long element with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyLong(names: Collection<String>, default: () -> R? = { null }, apply: Long.() -> R): R? = onAnyElement(names, default) { asLong.apply() }

/**
 * Performs an action on the first array with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArray(names: Collection<String>, default: () -> R? = { null }, apply: List<JsonElement>.() -> R): R? = onAnyElement(names) { asJsonArray.map { it } }?.apply() ?: default()

/**
 * Performs an action on the first array of strings with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArrayOfStrings(names: Collection<String>, default: () -> R? = { null }, apply: List<String>.() -> R): R? = onAnyArray(names, default) { mapNotNull { it.asString }.apply() }

/**
 * Performs an action on the first array of ints with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArrayOfInts(names: Collection<String>, default: () -> R? = { null }, apply: List<Int>.() -> R): R? = onAnyArray(names, default) { mapNotNull { it.asInt }.apply() }

/**
 * Performs an action on the first array of booleans with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArrayOfBooleans(names: Collection<String>, default: () -> R? = { null }, apply: List<Boolean>.() -> R): R? = onAnyArray(names, default) { mapNotNull { it.asBoolean }.apply() }

/**
 * Performs an action on the first array of doubles with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArrayOfDoubles(names: Collection<String>, default: () -> R? = { null }, apply: List<Double>.() -> R): R? = onAnyArray(names, default) { mapNotNull { it.asDouble }.apply() }

/**
 * Performs an action on the first array of longs with a name that matches any
 * of the given [names] and returns the result, or gets from the [default] supplier
 * if none is found.
 */
fun <R> JsonObject.onAnyArrayOfLongs(names: Collection<String>, default: () -> R? = { null }, apply: List<Long>.() -> R): R? = onAnyArray(names, default) { mapNotNull { it.asLong }.apply() }

/**
 * Returns the first element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyElement(names: Collection<String>, default: () -> JsonElement? = { null }): JsonElement? = onAnyElement(names, default) { this }

/**
 * Returns the first object with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyObject(names: Collection<String>, default: () -> JsonObject? = { null }): JsonObject? = onAnyObject(names, default) { this }

/**
 * Returns the first string element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyString(names: Collection<String>, default: () -> String? = { null }): String? = onAnyString(names, default) { this }

/**
 * Returns the first int element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyInt(names: Collection<String>, default: () -> Int? = { null }): Int? = onAnyInt(names, default) { this }

/**
 * Returns the first boolean element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyBoolean(names: Collection<String>, default: () -> Boolean? = { null }): Boolean? = onAnyBoolean(names, default) { this }

/**
 * Returns the first double element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyDouble(names: Collection<String>, default: () -> Double? = { null }): Double? = onAnyDouble(names, default) { this }

/**
 * Returns the first long element with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyLong(names: Collection<String>, default: () -> Long? = { null }): Long? = onAnyLong(names, default) { this }

/**
 * Returns the first array with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArray(names: Collection<String>, default: () -> List<JsonElement>? = { null }): List<JsonElement>? = onAnyArray(names, default) { this }

/**
 * Returns the first array of strings with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArrayOfStrings(names: Collection<String>, default: () -> List<String>? = { null }): List<String>? = onAnyArrayOfStrings(names, default) { this }

/**
 * Returns the first array of ints with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArrayOfInts(names: Collection<String>, default: () -> List<Int>? = { null }): List<Int>? = onAnyArrayOfInts(names, default) { this }

/**
 * Returns the first array of booleans with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArrayOfBooleans(names: Collection<String>, default: () -> List<Boolean>? = { null }): List<Boolean>? = onAnyArrayOfBooleans(names, default) { this }

/**
 * Returns the first array of doubles with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArrayOfDoubles(names: Collection<String>, default: () -> List<Double>? = { null }): List<Double>? = onAnyArrayOfDoubles(names, default) { this }

/**
 * Returns the first array of longs with a name that matches any of the given [names],
 * or gets from the [default] supplier if none is found.
 */
fun JsonObject.anyArrayOfLongs(names: Collection<String>, default: () -> List<Long>? = { null }): List<Long>? = onAnyArrayOfLongs(names, default) { this }

/**
 * Performs an action on the first element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyElementStrict(names: Collection<String>, default: () -> JsonElement? = { null }, apply: JsonElement.() -> R): R {
  return anyElement(names, default)?.apply()
    ?: throw NoSuchElementException("No element found with any of the given names: $names")
}

/**
 * Performs an action on the first object with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyObjectStrict(names: Collection<String>, default: () -> JsonObject? = { null }, apply: JsonObject.() -> R): R {
  return anyObject(names, default)?.apply()
    ?: throw NoSuchElementException("No object found with any of the given names: $names")
}

/**
 * Performs an action on the first string element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyStringStrict(names: Collection<String>, default: () -> String? = { null }, apply: String.() -> R): R {
  return anyString(names, default)?.apply()
    ?: throw NoSuchElementException("No string found with any of the given names: $names")
}

/**
 * Performs an action on the first int element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyIntStrict(names: Collection<String>, default: () -> Int? = { null }, apply: Int.() -> R): R {
  return anyInt(names, default)?.apply()
    ?: throw NoSuchElementException("No int found with any of the given names: $names")
}

/**
 * Performs an action on the first boolean element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyBooleanStrict(names: Collection<String>, default: () -> Boolean? = { null }, apply: Boolean.() -> R): R {
  return anyBoolean(names, default)?.apply()
    ?: throw NoSuchElementException("No boolean found with any of the given names: $names")
}

/**
 * Performs an action on the first double element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyDoubleStrict(names: Collection<String>, default: () -> Double? = { null }, apply: Double.() -> R): R {
  return anyDouble(names, default)?.apply()
    ?: throw NoSuchElementException("No double found with any of the given names: $names")
}

/**
 * Performs an action on the first long element with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyLongStrict(names: Collection<String>, default: () -> Long? = { null }, apply: Long.() -> R): R {
  return anyLong(names, default)?.apply()
    ?: throw NoSuchElementException("No long found with any of the given names: $names")
}

/**
 * Performs an action on the first array with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayStrict(names: Collection<String>, default: () -> List<JsonElement>? = { null }, apply: List<JsonElement>.() -> R): R {
  return anyArray(names, default)?.apply()
    ?: throw NoSuchElementException("No array found with any of the given names: $names")
}

/**
 * Performs an action on the first array of strings with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfStringsStrict(names: Collection<String>, default: () -> List<String>? = { null }, apply: List<String>.() -> R): R {
  return anyArrayOfStrings(names, default)?.apply()
    ?: throw NoSuchElementException("No array of strings found with any of the given names: $names")
}

/**
 * Performs an action on the first array of ints with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfIntsStrict(names: Collection<String>, default: () -> List<Int>? = { null }, apply: List<Int>.() -> R): R {
  return anyArrayOfInts(names, default)?.apply()
    ?: throw NoSuchElementException("No array of ints found with any of the given names: $names")
}

/**
 * Performs an action on the first array of booleans with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfBooleansStrict(names: Collection<String>, default: () -> List<Boolean>? = { null }, apply: List<Boolean>.() -> R): R {
  return anyArrayOfBooleans(names, default)?.apply()
    ?: throw NoSuchElementException("No array of booleans found with any of the given names: $names")
}

/**
 * Performs an action on the first array of doubles with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfDoublesStrict(names: Collection<String>, default: () -> List<Double>? = { null }, apply: List<Double>.() -> R): R {
  return anyArrayOfDoubles(names, default)?.apply()
    ?: throw NoSuchElementException("No array of doubles found with any of the given names: $names")
}

/**
 * Performs an action on the first array of longs with a name that matches any
 * of the given [names] and returns the result, falling back to the [default]
 * supplier, or throwing a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfLongsStrict(names: Collection<String>, default: () -> List<Long>? = { null }, apply: List<Long>.() -> R): R {
  return anyArrayOfLongs(names, default)?.apply()
    ?: throw NoSuchElementException("No array of longs found with any of the given names: $names")
}

/**
 * Returns the first element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyElementStrict(names: Collection<String>, default: () -> JsonElement? = { null }): JsonElement {
  return anyElement(names, default)
    ?: throw NoSuchElementException("No element found with any of the given names: $names")
}

/**
 * Returns the first object with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyObjectStrict(names: Collection<String>, default: () -> JsonObject? = { null }): JsonObject {
  return anyObject(names, default)
    ?: throw NoSuchElementException("No object found with any of the given names: $names")
}

/**
 * Returns the first string element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyStringStrict(names: Collection<String>, default: () -> String? = { null }): String {
  return anyString(names, default)
    ?: throw NoSuchElementException("No string found with any of the given names: $names")
}

/**
 * Returns the first int element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyIntStrict(names: Collection<String>, default: () -> Int? = { null }): Int {
  return anyInt(names, default)
    ?: throw NoSuchElementException("No int found with any of the given names: $names")
}

/**
 * Returns the first boolean element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyBooleanStrict(names: Collection<String>, default: () -> Boolean? = { null }): Boolean {
  return anyBoolean(names, default)
    ?: throw NoSuchElementException("No boolean found with any of the given names: $names")
}

/**
 * Returns the first double element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyDoubleStrict(names: Collection<String>, default: () -> Double? = { null }): Double {
  return anyDouble(names, default)
    ?: throw NoSuchElementException("No double found with any of the given names: $names")
}

/**
 * Returns the first long element with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyLongStrict(names: Collection<String>, default: () -> Long? = { null }): Long {
  return anyLong(names, default)
    ?: throw NoSuchElementException("No long found with any of the given names: $names")
}

/**
 * Returns the first array with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayStrict(names: Collection<String>, default: () -> List<JsonElement>? = { null }): List<JsonElement> {
  return anyArray(names, default)
    ?: throw NoSuchElementException("No array found with any of the given names: $names")
}

/**
 * Returns the first array of strings with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayOfStringsStrict(names: Collection<String>, default: () -> List<String>? = { null }): List<String> {
  return anyArrayOfStrings(names, default)
    ?: throw NoSuchElementException("No array of strings found with any of the given names: $names")
}

/**
 * Returns the first array of ints with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayOfIntsStrict(names: Collection<String>, default: () -> List<Int>? = { null }): List<Int> {
  return anyArrayOfInts(names, default)
    ?: throw NoSuchElementException("No array of ints found with any of the given names: $names")
}

/**
 * Returns the first array of booleans with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayOfBooleansStrict(names: Collection<String>, default: () -> List<Boolean>? = { null }): List<Boolean> {
  return anyArrayOfBooleans(names, default)
    ?: throw NoSuchElementException("No array of booleans found with any of the given names: $names")
}

/**
 * Returns the first array of doubles with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayOfDoublesStrict(names: Collection<String>, default: () -> List<Double>? = { null }): List<Double> {
  return anyArrayOfDoubles(names, default)
    ?: throw NoSuchElementException("No array of doubles found with any of the given names: $names")
}

/**
 * Returns the first array of longs with a name that matches any of the given [names],
 * attempts to fall back to the [default] supplier if none is found, or throws a [NoSuchElementException]
 * if null is found.
 */
fun JsonObject.anyArrayOfLongsStrict(names: Collection<String>, default: () -> List<Long>? = { null }): List<Long> {
  return anyArrayOfLongs(names, default)
    ?: throw NoSuchElementException("No array of longs found with any of the given names: $names")
}

/**
 * Performs an action on the first element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyElementStrict(names: Collection<String>, apply: JsonElement.() -> R): R {
  return anyElement(names)?.apply()
    ?: throw NoSuchElementException("No element found with any of the given names: $names")
}

/**
 * Performs an action on the first object with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyObjectStrict(names: Collection<String>, apply: JsonObject.() -> R): R {
  return anyObject(names)?.apply()
    ?: throw NoSuchElementException("No object found with any of the given names: $names")
}

/**
 * Performs an action on the first string element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyStringStrict(names: Collection<String>, apply: String.() -> R): R {
  return anyString(names)?.apply()
    ?: throw NoSuchElementException("No string found with any of the given names: $names")
}

/**
 * Performs an action on the first int element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyIntStrict(names: Collection<String>, apply: Int.() -> R): R {
  return anyInt(names)?.apply()
    ?: throw NoSuchElementException("No int found with any of the given names: $names")
}

/**
 * Performs an action on the first boolean element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyBooleanStrict(names: Collection<String>, apply: Boolean.() -> R): R {
  return anyBoolean(names)?.apply()
    ?: throw NoSuchElementException("No boolean found with any of the given names: $names")
}

/**
 * Performs an action on the first double element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyDoubleStrict(names: Collection<String>, apply: Double.() -> R): R {
  return anyDouble(names)?.apply()
    ?: throw NoSuchElementException("No double found with any of the given names: $names")
}

/**
 * Performs an action on the first long element with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyLongStrict(names: Collection<String>, apply: Long.() -> R): R {
  return anyLong(names)?.apply()
    ?: throw NoSuchElementException("No long found with any of the given names: $names")
}

/**
 * Performs an action on the first array with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayStrict(names: Collection<String>, apply: List<JsonElement>.() -> R): R {
  return anyArray(names)?.apply()
    ?: throw NoSuchElementException("No array found with any of the given names: $names")
}

/**
 * Performs an action on the first array of strings with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfStringsStrict(names: Collection<String>, apply: List<String>.() -> R): R {
  return anyArrayOfStrings(names)?.apply()
    ?: throw NoSuchElementException("No array of strings found with any of the given names: $names")
}

/**
 * Performs an action on the first array of ints with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfIntsStrict(names: Collection<String>, apply: List<Int>.() -> R): R {
  return anyArrayOfInts(names)?.apply()
    ?: throw NoSuchElementException("No array of ints found with any of the given names: $names")
}

/**
 * Performs an action on the first array of booleans with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfBooleansStrict(names: Collection<String>, apply: List<Boolean>.() -> R): R {
  return anyArrayOfBooleans(names)?.apply()
    ?: throw NoSuchElementException("No array of booleans found with any of the given names: $names")
}

/**
 * Performs an action on the first array of doubles with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfDoublesStrict(names: Collection<String>, apply: List<Double>.() -> R): R {
  return anyArrayOfDoubles(names)?.apply()
    ?: throw NoSuchElementException("No array of doubles found with any of the given names: $names")
}

/**
 * Performs an action on the first array of longs with a name that matches any
 * of the given [names] and returns the result, or throws a [NoSuchElementException] if none is found.
 */
fun <R> JsonObject.onAnyArrayOfLongsStrict(names: Collection<String>, apply: List<Long>.() -> R): R {
  return anyArrayOfLongs(names)?.apply()
    ?: throw NoSuchElementException("No array of longs found with any of the given names: $names")
}

/**
 * Returns the first element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyElementStrict(names: Collection<String>): JsonElement = anyElementStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first object with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyObjectStrict(names: Collection<String>): JsonObject = anyObjectStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first string element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyStringStrict(names: Collection<String>): String = anyStringStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first int element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyIntStrict(names: Collection<String>): Int = anyIntStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first boolean element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyBooleanStrict(names: Collection<String>): Boolean = anyBooleanStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first double element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyDoubleStrict(names: Collection<String>): Double = anyDoubleStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first long element with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyLongStrict(names: Collection<String>): Long = anyLongStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first array of strings with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyArrayOfStringsStrict(names: Collection<String>): List<String> = anyArrayOfStringsStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first array of ints with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyArrayOfIntsStrict(names: Collection<String>): List<Int> = anyArrayOfIntsStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first array of booleans with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyArrayOfBooleansStrict(names: Collection<String>): List<Boolean> = anyArrayOfBooleansStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first array of doubles with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyArrayOfDoublesStrict(names: Collection<String>): List<Double> = anyArrayOfDoublesStrict(names) { throw NoSuchElementException() }

/**
 * Returns the first array of longs with a name that matches any of the given [names],
 * or throws a [NoSuchElementException] if none is found.
 */
fun JsonObject.anyArrayOfLongsStrict(names: Collection<String>): List<Long> = anyArrayOfLongsStrict(names) { throw NoSuchElementException() }