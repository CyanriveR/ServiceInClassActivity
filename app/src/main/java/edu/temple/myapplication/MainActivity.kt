package edu.temple.myapplication

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {


        lateinit var timerBinder : TimerService.TimerBinder
        var isconnected = false
        val serviceConnection = object: ServiceConnection{
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            timerBinder = service as TimerService.TimerBinder
            isconnected= true
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            isconnected = false
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindService(
            Intent(this, TimerService::class.java),
            serviceConnection,
            BIND_AUTO_CREATE
        )
        findViewById<Button>(R.id.startButton).setOnClickListener {

        }

        findViewById<Button>(R.id.pauseButton).setOnClickListener {

        }

        findViewById<Button>(R.id.stopButton).setOnClickListener {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var flag : Boolean = false
        while(!flag){
        when(item.itemId){
            R.id.startButton -> {if(isconnected) timerBinder.start(50)
                flag= true}
            R.id.stopButton ->  {if(isconnected) timerBinder.stop()
                flag=true}
            R.id.pauseButton-> {if(isconnected) timerBinder.pause()
                flag=true}
        }}
        return super.onOptionsItemSelected(item)
    }
}