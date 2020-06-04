package com.techmahindra.techmEmployeePortal.core

import android.app.Application
import android.content.Context

/**
 * TechmEmployeeApplicationContext - Application base class
 */
class TechmEmployeeApplication : Application() {

    companion object {
        lateinit var context: Context
    }

  // initialize the context
    override fun onCreate() {
        super.onCreate()
        context = this

    }
}