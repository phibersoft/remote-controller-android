package com.phibersoft.remoteph.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.phibersoft.remoteph.helpers.EventHandler
import com.phibersoft.remoteph.helpers.SocketManager
import com.phibersoft.remoteph.helpers.Storage
import io.socket.client.Socket

// Minimal
const val NOTIFY_NEXT = "com.phibersoft.remoteph.next"
const val NOTIFY_PREV = "com.phibersoft.remoteph.prev"
const val NOTIFY_PLAY = "com.phibersoft.remoteph.play"
const val NOTIFY_VOL_UP = "com.phibersoft.remoteph.vol_up"
const val NOTIFY_VOL_DOWN = "com.phibersoft.remoteph.vol_down"

// Expanded
const val NOTIFY_LEFT = "com.phibersoft.remoteph.left"
const val NOTIFY_RIGHT = "com.phibersoft.remoteph.right"
const val NOTIFY_TAB = "com.phibersoft.remoteph.tab"
const val NOTIFY_ENTER = "com.phibersoft.remoteph.enter"
const val NOTIFY_FULLSCREEN = "com.phibersoft.remoteph.full_screen"


class NotificationService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        SocketManager.establishConnection()

        val action = intent!!.action
        Log.d("onStartCommand", "action=$action")
        when (action) {
            NOTIFY_PREV -> {
                handleEvent("left")
            }
            NOTIFY_PLAY -> {
                handleEvent("space")
            }
            NOTIFY_NEXT -> {
                handleEvent("right")
            }
            NOTIFY_VOL_UP -> {
                handleEvent("audio_vol_up")
            }
            NOTIFY_VOL_DOWN -> {
                handleEvent("audio_vol_down")
            }
            NOTIFY_LEFT -> {
                handleEvent("audio_prev")
            }
            NOTIFY_RIGHT -> {
                handleEvent("audio_next")
            }
            NOTIFY_ENTER -> {
                handleEvent("enter")
            }
            NOTIFY_TAB -> {
                handleEvent("tab")
            }
            NOTIFY_FULLSCREEN -> {
                handleEvent("f")
            }
        }
        return START_STICKY
    }

    private fun handleEvent(ev: String) {
        SocketManager.emitEvent(EventHandler.CreateMediaEvent(ev, listOf()))
    }

}