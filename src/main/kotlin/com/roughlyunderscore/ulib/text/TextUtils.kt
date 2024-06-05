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

package com.roughlyunderscore.ulib.text

import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.hover.content.Text
import org.bukkit.ChatColor
import java.util.UUID

/**
 * Checks if a list contains a [string], ignoring case.
 */
fun List<String>.containsIgnoreCase(string: String): Boolean = this.any { it.equals(string, ignoreCase = true) }

/**
 * Checks if a list of strings contains any item from the [other]
 * list of strings, ignoring case.
 */
fun List<String>.containsAnyIgnoreCase(other: List<String>): Boolean = other.any { this.containsIgnoreCase(it) }

/**
 * Joins a list of strings with [prefix] and [suffix], and [separator] between each string.
 * For example, if the list consists of "a" and "b", and the prefix is "<", the suffix is ">",
 * and the separator is ", ", then the result will be "<a>, <b>".
 *
 * Sometimes there might be a trailing separator (e.g. "<a>, <b>,"). [forceRemoveSeparator] (true
 * by default) will remove any characters that match the separator in the end.
 */
fun Iterable<String?>.joinAndWrap(prefix: String, suffix: String, separator: String, forceRemoveSeparator: Boolean = true): String {
  val mutable = this.toMutableList()
  for (i in mutable.indices) {
    mutable[i] = "$prefix${mutable[i]}$suffix"
  }

  // Add separators to everything but last
  for (i in 0 ..< mutable.size - 1) {
    mutable[i] = "${mutable[i]}$separator"
  }

  return mutable.joinToString("").let { str ->
    if (forceRemoveSeparator) str.removeSuffix(separator)
    else str
  }
}

/**
 * Translates all the string's color codes (Spigot). Deprecated in Paper.
 */
fun String.formatColor() = ChatColor.translateAlternateColorCodes('&', this)

/**
 * Translates all the string's color codes (Bungee).
 */
fun String.formatColorBungee() = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', this)

/**
 * Returns a lowercase version of this string with no underscores or dashes.
 */
fun String.normalize(): String = this.lowercase().replace("_", "").replace("-", "")

/**
 * Converts an integer to a roman numeral.
 * Code from https://stackoverflow.com/a/39411250
 */
fun Int.toRoman(): String = "I".repeat(this)
  .replace("IIIII", "V")
  .replace("IIII", "IV")
  .replace("VV", "X")
  .replace("VIV", "IX")
  .replace("XXXXX", "L")
  .replace("XXXX", "XL")
  .replace("LL", "C")
  .replace("LXL", "XC")
  .replace("CCCCC", "D")
  .replace("CCCC", "CD")
  .replace("DD", "M")
  .replace("DCD", "CM")

/**
 * Replaces all occurrences of [strings] with [replacement].
 */
fun String.replaceMany(replacement: String, vararg strings: String): String {
  var newString = this
  for (string in strings) {
    newString = newString.replace(string, replacement)
  }
  return newString
}

/**
 * Converts a string to a UUID, or null if the string is null or an invalid UUID.
 */
fun String?.safeUuid(): UUID? = try {
  this?.let { UUID.fromString(it) }
} catch (e: IllegalArgumentException) {
  null
}

/**
 * Builds a clickable Component with a hover [prompt] and a [command] (Spigot).
 */
fun String.clickable(prompt: String, command: String): BaseComponent = ComponentBuilder(this)
  .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(prompt)))
  .event(ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command))
  .build()

/**
 * Builds a clickable Component with a hover [prompt] and a [link] (Spigot).
 */
fun String.link(prompt: String, link: String): BaseComponent = ComponentBuilder(this)
  .event(HoverEvent(HoverEvent.Action.SHOW_TEXT, Text(prompt)))
  .event(ClickEvent(ClickEvent.Action.OPEN_URL, link))
  .build()

/**
 * Checks if the string ends with any of the [suffixes].
 */
fun String.endsWithAny(vararg suffixes: String) = suffixes.any { endsWith(it) }