package com.example.messengerservice_demo

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast

class RemoteService :Service(){

    var myms=Messenger(IncomingHandler())

    inner class IncomingHandler:Handler(){

        override fun handleMessage(msg: Message) {
            var data=msg.data
            var ds=data.getString("mydata")
            Toast.makeText(applicationContext, ds, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myms.binder
    }

}