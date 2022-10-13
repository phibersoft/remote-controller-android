package com.phibersoft.remoteph.helpers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.phibersoft.remoteph.R
import com.phibersoft.remoteph.services.*

class Notifier(
    private val context: Context
) {
    @RequiresApi(Build.VERSION_CODES.N)
    private val importance: Int = NotificationManager.IMPORTANCE_HIGH
    private val channelName = "com.phibersoft.remoteph"

    private fun getNotificationBuilder() {

    }

    fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "PhiberChannel"
            val desc = "PhiberChannel notifications"
            val channel =
                NotificationChannel(channelName, name, importance)
            channel.description = desc
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC

            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        } else {
            Toast.makeText(context, "Your device not supporting notifications", Toast.LENGTH_LONG)
                .show()
        }
    }


    internal fun notify() {
        val notificationLayout = RemoteViews(context.packageName, R.layout.notification_normal)
        val notificationLayoutBig = RemoteViews(context.packageName, R.layout.notification_big)
        setListeners(notificationLayout, notificationLayoutBig)

        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, channelName)
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutBig)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setSound(null)
                .setPriority(NotificationManager.IMPORTANCE_MAX)
        val manager: NotificationManagerCompat = NotificationManagerCompat.from(context)

        manager.notify(331, builder.build())
    }


    private fun setListeners(smallView: RemoteViews, bigView: RemoteViews) {
        val intentPrev = Intent(context, NotificationService::class.java)
        intentPrev.action = NOTIFY_PREV
        val pendingIntentPrev =
            PendingIntent.getService(context, 0, intentPrev, PendingIntent.FLAG_MUTABLE)
        smallView.setOnClickPendingIntent(R.id.rcLeft, pendingIntentPrev)
        bigView.setOnClickPendingIntent(R.id.rcLeft, pendingIntentPrev)

        val intentPlay = Intent(context, NotificationService::class.java)
        intentPlay.action = NOTIFY_PLAY
        val pendingIntentPlay =
            PendingIntent.getService(context, 0, intentPlay, PendingIntent.FLAG_MUTABLE)
        smallView.setOnClickPendingIntent(R.id.rcPlay, pendingIntentPlay)
        bigView.setOnClickPendingIntent(R.id.rcPlay, pendingIntentPlay)

        val intentNext = Intent(context, NotificationService::class.java)
        intentNext.action = NOTIFY_NEXT
        val pendingIntentNext =
            PendingIntent.getService(context, 0, intentNext, PendingIntent.FLAG_MUTABLE)
        smallView.setOnClickPendingIntent(R.id.rcRight, pendingIntentNext)
        bigView.setOnClickPendingIntent(R.id.rcRight, pendingIntentNext)

        val intentVolDown = Intent(context, NotificationService::class.java)
        intentVolDown.action = NOTIFY_VOL_DOWN
        val pendingIntentVolDown =
            PendingIntent.getService(context, 0, intentVolDown, PendingIntent.FLAG_MUTABLE)
        smallView.setOnClickPendingIntent(R.id.rcSoundDown, pendingIntentVolDown)
        bigView.setOnClickPendingIntent(R.id.rcSoundDown, pendingIntentVolDown)

        val intentVolUp = Intent(context, NotificationService::class.java)
        intentVolUp.action = NOTIFY_VOL_UP
        val pendingIntentVolUp =
            PendingIntent.getService(context, 0, intentVolUp, PendingIntent.FLAG_MUTABLE)
        smallView.setOnClickPendingIntent(R.id.rcSoundUp, pendingIntentVolUp)
        bigView.setOnClickPendingIntent(R.id.rcSoundUp, pendingIntentVolUp)

        // Expanded intents

        val intentLeft = Intent(context, NotificationService::class.java)
        intentLeft.action = NOTIFY_LEFT
        val pendingIntentLeft =
            PendingIntent.getService(context, 0, intentLeft, PendingIntent.FLAG_MUTABLE)
        bigView.setOnClickPendingIntent(R.id.rcPrev, pendingIntentLeft)

        val intentRight = Intent(context, NotificationService::class.java)
        intentRight.action = NOTIFY_RIGHT
        val pendingIntentRight =
            PendingIntent.getService(context, 0, intentRight, PendingIntent.FLAG_MUTABLE)
        bigView.setOnClickPendingIntent(R.id.rcNext, pendingIntentRight)

        val intentTab = Intent(context, NotificationService::class.java)
        intentTab.action = NOTIFY_TAB
        val pendingIntentTab =
            PendingIntent.getService(context, 0, intentTab, PendingIntent.FLAG_MUTABLE)
        bigView.setOnClickPendingIntent(R.id.rcTab, pendingIntentTab)

        val intentEnter = Intent(context, NotificationService::class.java)
        intentEnter.action = NOTIFY_ENTER
        val pendingIntentEnter =
            PendingIntent.getService(context, 0, intentEnter, PendingIntent.FLAG_MUTABLE)
        bigView.setOnClickPendingIntent(R.id.rcEnter, pendingIntentEnter)

        val intentFullScreen = Intent(context, NotificationService::class.java)
        intentFullScreen.action = NOTIFY_FULLSCREEN
        val pendingIntentFullScreen =
            PendingIntent.getService(
                context,
                0,
                intentFullScreen,
                PendingIntent.FLAG_MUTABLE
            )
        bigView.setOnClickPendingIntent(R.id.rcFullScreen, pendingIntentFullScreen)
    }
}