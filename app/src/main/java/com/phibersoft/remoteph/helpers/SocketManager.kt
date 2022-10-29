package com.phibersoft.remoteph.helpers

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket

object SocketManager {
    private lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(uri: String) {
        try {
            mSocket = IO.socket(uri)
        } catch (e: Exception) {
            Log.e("SocketManager", "Error setting socket: $e")
            mSocket = IO.socket("http://192.168.1.3")
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    @Synchronized
    fun establishConnection() {
        if (!mSocket.connected()) {
            mSocket.connect()
        }
    }

    @Synchronized
    fun disconnect() {
        mSocket.disconnect()
    }

    fun emitEvent(args: List<Any?>, firstRun: Boolean = true): Boolean {
        Log.i("PHIBER-emitEvent", args.toString())
        return if (mSocket.connected()) {
            mSocket.emit(args[0] as String, args[1], args[2])
            true
        } else {
            if (firstRun) {
                establishConnection()
                return emitEvent(args, false)
            } else {
                return false
            }
        }
    }
}