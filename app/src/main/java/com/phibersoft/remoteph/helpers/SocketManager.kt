package com.phibersoft.remoteph.helpers

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket

object SocketManager {
    lateinit var mSocket: Socket

    @Synchronized
    fun setSocket(uri: String) {
        try {
            mSocket = IO.socket(uri)
        } catch (e: Exception) {
            Log.i("PHIBER-ERROR", "Error: ${e.message.toString()}")
            mSocket = IO.socket("http://192.168.1.3")
        }
    }

    @Synchronized
    fun getSocket(): Socket {
        return mSocket
    }

    fun emitEvent(args: List<Any?>, socket: Socket): Boolean {
        Log.i("PHIBER-emitEvent", args.toString())
        if (socket.connected()) {
            socket.emit(args[0] as String, args[1], args[2])
            return true;
        } else {
            return false;
        }
    }
}