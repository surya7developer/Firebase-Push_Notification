package com.suresh.fcm.notification

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {

    private var FCM_TOKEN = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFcmToken()
        getFirebaseNotificationFromConsole()


    }

    private fun getFcmToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener {

            if (it.isSuccessful) {
                FCM_TOKEN = it.result
                Log.d(TAG, "getFcmToken: token = $FCM_TOKEN")
            } else {
                Log.d(TAG, "getFcmToken: Error = ${it.exception}")
            }
        }
    }

    private fun getFirebaseNotificationFromConsole() {

        val textView = findViewById<TextView>(R.id.textView)
        val stringBuilder = StringBuilder()

        // I have passed these key value from firebase console push notification
        // In this way we can pass data in our application
        // You can set value in firebase console and then fire notification
        // It will print here as key value
        // I have set it in textview as well print log

        Log.d(TAG, "intent = $intent")
        if (intent != null && intent.hasExtra("Name")) {

            for (keys in intent.extras?.keySet()!!) {
                stringBuilder.append(intent.getStringExtra(keys).toString())
                stringBuilder.append("\n")
                Log.d("MainFirebaseTag", "key = $keys : data = ${intent.getStringExtra(keys)}")
            }
        }

        textView.text = stringBuilder.toString()


        // Output

        // intent = Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] flg=0x14000000 pkg=com.suresh.fcm.notification cmp=com.suresh.fcm.notification/.MainActivity (has extras) }
        // key = google.delivered_priority : data = high
        // key = google.sent_time : data = null
        // key = google.ttl : data = null
        // key = google.original_priority : data = high
        // key = google.product_id : data = null
        // key = Age : data = 26
        // key = Cast : data = Prajapati
        // key = City : data = Ahmedabad
        // key = Name : data = Suresh
        // key = from : data = 799394128165
        // key = google.message_id : data = 0:1726762509696505%de8ab9a0de8ab9a0
        // key = gcm.n.analytics_data : data = null
        // key = collapse_key : data = com.suresh.fcm.notification
    }

    companion object {
        val TAG = "MainFirebaseTag"
    }
}