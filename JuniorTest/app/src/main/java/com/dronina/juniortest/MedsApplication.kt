package com.dronina.juniortest

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class MedsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }
}