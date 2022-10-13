package com.phibersoft.remoteph.ui.lol

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.phibersoft.remoteph.databinding.FragmentLolBinding
import com.phibersoft.remoteph.helpers.EventHandler
import com.phibersoft.remoteph.helpers.SocketManager
import com.phibersoft.remoteph.ui.SuperFragment

class LoLFragment : SuperFragment() {
    private var _binding: FragmentLolBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLolBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mSock = super.afterCreateView(binding.refreshButton)

        bindListeners()

        return root
    }

    private fun bindListeners() {
        // route : lol
        // accept-match, choose-hero, select-hero

        binding.acceptMatch.setOnClickListener {
            SocketManager.emitEvent(EventHandler.CreateLoLEvent("accept-match"), mSock)
        }


        binding.btnPickHero.setOnClickListener {
            SocketManager.emitEvent(
                EventHandler.CreateLoLEvent("choose-hero"),
                mSock
            )
        }

        binding.btnChooseHero.setOnClickListener {
            SocketManager.emitEvent(EventHandler.CreateLoLEvent("select-hero", getHeroName()), mSock)
        }
    }

    private fun getHeroName(): String {
        return binding.etChampion.text.toString().toLowerCase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mSock.disconnect()
    }
}