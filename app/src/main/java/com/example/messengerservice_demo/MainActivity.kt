package com.example.messengerservice_demo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var send:Button

    var ms:Messenger?=null
    var isBound:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var i=Intent(applicationContext,RemoteService::class.java)
        bindService(i,myconn,Context.BIND_AUTO_CREATE)

        send=findViewById(R.id.send)

        send.setOnClickListener {

            if (!isBound) return@setOnClickListener

            var msg=Message.obtain()

            var bnd=Bundle()
            bnd.putString("mydata","Message done!!")
            msg.data=bnd

            try {
                ms!!.send(msg)
            } catch (e:RemoteException){
                e.printStackTrace()
            }

        }
    }

    var myconn= object : ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            ms= Messenger(service)
            isBound=true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            ms=null
            isBound=false
        }
    }

}