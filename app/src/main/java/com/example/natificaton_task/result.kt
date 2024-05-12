package com.example.natificaton_task

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class result : AppCompatActivity() {
    lateinit var feedback: Button

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null)
        val builder = AlertDialog.Builder(this@result)
        builder.setView(dialogView)
        val Dialog = builder.create()
        Dialog.setCancelable(false)
        val title = dialogView.findViewById<TextView>(R.id.dialog_title)
        val Rating = dialogView.findViewById<RatingBar>(R.id.rating_bar)
        val edit = dialogView.findViewById<EditText>(R.id.edit)
        val positiveButton = dialogView.findViewById<Button>(R.id.positive_button)
        val negativeButton = dialogView.findViewById<Button>(R.id.negative_button)

        val channel = NotificationChannel(
            "channel_one",
            "Channel One",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            "Notifications"
        }
        val notificationManger =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManger.createNotificationChannel(channel)

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivities(
                this, 20,
                arrayOf(intent), PendingIntent.FLAG_IMMUTABLE
            )

        feedback = findViewById(R.id.feedback)
        feedback.setOnClickListener {
            positiveButton.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        100
                    )
                } else {
                    var sendNotification = NotificationCompat.Builder(this, "channel_one")
                        .setSmallIcon(android.R.drawable.ic_popup_reminder)
                        .setContentTitle(resources.getString(R.string.Content_Title))
                        .setContentText(
                            "${resources.getString(R.string.rating)} : ${Rating.rating} \n" +
                                    "${resources.getString(R.string.feedback_message)} : ${edit.text}"
                        )
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setContentIntent(pendingIntent)
                        .build()

                    NotificationManagerCompat.from(this).notify(1, sendNotification)
                }
                Dialog.dismiss()
            }
            negativeButton.setOnClickListener {
                Dialog.dismiss()
                Toast.makeText(applicationContext, "No", Toast.LENGTH_SHORT).show()
            }
            Dialog.show()
        }
    }
}