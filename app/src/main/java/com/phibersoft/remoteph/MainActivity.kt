package com.phibersoft.remoteph

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.phibersoft.remoteph.databinding.ActivityMainBinding
import com.phibersoft.remoteph.helpers.Notifier
import com.phibersoft.remoteph.helpers.SocketManager
import com.phibersoft.remoteph.helpers.Storage


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("PHIBER", "MainActivity.onCreate")
        SocketManager.setSocket(
            Storage.getValue(
                this,
                "server_uri",
                ""
            )
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navView.setupWithNavController(navController)


        notificationHandler()
    }


    private fun notificationHandler() {
        val notifier = Notifier(this)
        notifier.createChannel()
        notifier.notify()
    }
}
