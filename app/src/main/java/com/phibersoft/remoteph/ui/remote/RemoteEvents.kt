package com.phibersoft.remoteph.ui.remote

import com.phibersoft.remoteph.databinding.FragmentRemoteBinding

fun RemoteEvents(binding: FragmentRemoteBinding): MutableList<List<Any>> {
    val events: MutableList<List<Any>> = mutableListOf()

    // Desktop Control
    events.add(listOf(binding.controlMute, "audio_mute"))
    events.add(listOf(binding.controlVolumeDown, "audio_vol_down"))
    events.add(listOf(binding.controlVolumeUp, "audio_vol_up"))
    events.add(listOf(binding.controlPause, "audio_pause"))
    events.add(listOf(binding.controlNext, "audio_next"))
    events.add(listOf(binding.controlPrevious, "audio_prev"))

    // Remote Control
    events.add(listOf(binding.controlUp, "up"))
    events.add(listOf(binding.controlRight, "right"))
    events.add(listOf(binding.controlLeft, "left"))
    events.add(listOf(binding.controlDown, "down"))
    events.add(listOf(binding.controlSpace, "space"))

    // Other keyboard controls
    events.add(listOf(binding.controlFullscreen, "f"))
    events.add(listOf(binding.controlTab, "tab"))
    events.add(listOf(binding.controlShiftTab, "tab", listOf("shift")))
    events.add(listOf(binding.controlEnter, "enter"))

    return events
}