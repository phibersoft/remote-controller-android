package com.phibersoft.remoteph.ui

import android.content.Context
import android.widget.Button
import androidx.fragment.app.Fragment
import com.phibersoft.remoteph.R
import com.phibersoft.remoteph.helpers.SocketManager
import io.socket.client.Socket

open class SuperFragment : Fragment() {

    lateinit var mSock: Socket


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mSock = SocketManager.getSocket()

        // if mSock is not connected, force a reconnect
        if (!mSock.connected()) {
            SocketManager.establishConnection()
        }
    }

    fun afterCreateView(button: Button): Socket {
        handleRefreshButton(button)

        return mSock
    }

    private fun handleRefreshButton(button: Button) {
        listener(button, mSock)
        button.setOnClickListener {
            listener(button, mSock)
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

            sock.connect().on("connect") {
                successButton(button)
            }.on("connect_error") {
                failureButton(button)
            }
        }
    }
}