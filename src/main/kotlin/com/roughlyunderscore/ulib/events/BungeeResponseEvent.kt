// Copyright 2023 RoughlyUnderscore
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

package com.roughlyunderscore.ulib.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * An event that is fired when a response is received from the API.
 * This event does not represent anything on its own; instead, adapter plugins
 * are meant to extend this class for their own response events.
 * Most plugins will have no use for this event.
 */
open class BungeeResponseEvent : Event() {
  companion object {
    @JvmStatic private val HANDLERS = HandlerList()
    @JvmStatic fun getHandlerList(): HandlerList = HANDLERS
  }

  override fun getHandlers(): HandlerList = HANDLERS
}