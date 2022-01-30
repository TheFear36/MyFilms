package com.thefear.myfilms.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.thefear.myfilms.model.FilmsLoader
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.model.repository.Genre

class MyService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //TODO
        return START_NOT_STICKY
    }

}