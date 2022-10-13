package com.phibersoft.remoteph.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.phibersoft.remoteph.databinding.FragmentSettingsBinding
import com.phibersoft.remoteph.helpers.Notifier
import com.phibersoft.remoteph.helpers.Storage

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val uriInput = binding.etServerUri

        uriInput.setText(
            Storage.getValue(
                this.context as Context,
                "server_uri",
                "http://192.168.1.6:9292"
            )
        )

        binding.btSaveChanges.setOnClickListener {
            saveChanges()
        }

        binding.fireNotify.setOnClickListener {
            val notifier = Notifier(context as Context)
            notifier.createChannel()
            notifier.notify()
        }
        return root
    }

    private fun saveChanges() {
        val url = binding.etServerUri.text.toString()

        Storage.setValue(this.context as Context, "server_uri", url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}