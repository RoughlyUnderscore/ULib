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

package com.roughlyunderscore.ulib.plugin

import com.google.common.io.ByteStreams
import com.roughlyunderscore.ulib.data.Time
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

/**
 * Sends a plugin message to BungeeCord on behalf of the plugin. There must be at least one player online
 * on the server, otherwise the message will not be sent. [block] is executed after sending the message.
 */
fun Plugin.sendPluginMessage(player: Player?, channel: String, vararg arguments: String, block: () -> Unit) {
  if (player != null) {
    val out = ByteStreams.newDataOutput()
    for (arg in arguments) out.writeUTF(arg)

    val bytes = out.toByteArray()
    player.sendPluginMessage(this, channel, bytes)

    block()
  } else {
    val randomPlayer = server.onlinePlayers.firstOrNull()
    if (randomPlayer != null) sendPluginMessage(randomPlayer, channel, *arguments, block = block)
  }
}

/**
 * Waits some [time] and executes a [block]. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.wait(time: Time, block: () -> Unit) =
  Bukkit.getScheduler().runTaskLater(this, block, time.ticks)

/**
 * Runs a [block] periodically after a [delay]. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.periodical(delay: Time, period: Time, block: () -> Unit) =
  Bukkit.getScheduler().runTaskTimer(this, block, delay.ticks, period.ticks)

/**
 * Waits some [time] and executes a [block] asynchronously. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.waitAsync(time: Time, block: () -> Unit) =
  Bukkit.getScheduler().runTaskLaterAsynchronously(this, block, time.ticks)

/**
 * Runs a [block] asynchronously periodically after a [delay]. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.periodicalAsync(delay: Time, period: Time, block: () -> Unit) =
  Bukkit.getScheduler().runTaskTimerAsynchronously(this, block, delay.ticks, period.ticks)

/**
 * Runs a [block] after the next tick. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.nextTick(block: () -> Unit) =
  Bukkit.getServer().scheduler.runTask(this, block)

/**
 * Runs a [block] after the next tick asynchronously. Uses a regular Bukkit scheduler.
 * Returns the resulting BukkitTask.
 */
fun Plugin.nextTickAsync(block: () -> Unit) =
  Bukkit.getServer().scheduler.runTaskAsynchronously(this, block)