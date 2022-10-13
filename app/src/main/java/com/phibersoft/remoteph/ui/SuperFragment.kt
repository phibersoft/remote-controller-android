package com.phibersoft.remoteph.ui

import android.content.Context
import android.widget.Button
import androidx.fragment.app.Fragment
import com.phibersoft.remoteph.R
import com.phibersoft.remoteph.helpers.SocketManager
import com.phibersoft.remoteph.helpers.Storage
import io.socket.client.Socket

open class SuperFragment : Fragment() {

    lateinit var mSock: Socket

    fun afterCreateView(button: Button): Socket {
        SocketManager.setSocket(Storage.getValue(context as Context, "server_uri", ""))
        val sock = SocketManager.mSocket

        handleRefreshButton(button, sock)

        return sock
    }

    private fun handleRefreshButton(button: Button, sock: Socket) {
        listener(button, sock)
        button.setOnClickListener {
            listener(button, sock)
        }
    }

    private fun failureButton(button: Button) {
        button.setBackgroundColor(resources.getColor(R.color.danger))
        button.text = "Failed"
    }

    private fun successButton(button: Button) {
        button.setBackgroundColor(resources.getColor(R.color.success))
        button.text = "Connected"
    }

    private fun listener(button: Button, sock: Socket) {
        if (sock.connected()) {
            successButton(button)
        } else {
            failureButton(button)

            sock.connect()
                .on("connect") {
                    successButton(button)
                }
                .on("connect_error") {
                    failureButton(button)
                }
        }
    }
}