package com.phibersoft.remoteph.ui.remote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.phibersoft.remoteph.databinding.FragmentRemoteBinding
import com.phibersoft.remoteph.helpers.EventHandler
import com.phibersoft.remoteph.helpers.SocketManager
import com.phibersoft.remoteph.ui.SuperFragment


class RemoteFragment : SuperFragment() {
    private var _binding: FragmentRemoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRemoteBinding.inflate(inflater, container, false)
        val root: View = binding.root

        super.afterCreateView(binding.refreshButton)

        bindListeners()
        return root
    }

    private fun bindListeners() {
        for (i in RemoteEvents(binding)) {
            bindListener(
                i[0] as Button,
                i[1] as String,
                if (i.size == 3) (i[2] as List<String>) else listOf<String>()
            )
        }
    }

    private fun bindListener(button: Button, key: String, modifs: List<String> = listOf()) {
        button.setOnClickListener {
            SocketManager.emitEvent(EventHandler.CreateMediaEvent(key, modifs))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        SocketManager.disconnect()
    }
}