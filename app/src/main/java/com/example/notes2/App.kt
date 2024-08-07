package com.example.notes2

import android.app.Application
import androidx.room.Room
import com.example.notes2.data.db.AppDatabase
import com.example.notes2.utils.PreferenceHelper

class App : Application() {

    companion object {
        var appDatabase: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        getInstance()
    }
    fun getInstance(): AppDatabase? {
        if (appDatabase == null) {
            appDatabase = applicationContext.let {
                Room.databaseBuilder(
                    it,
                    AppDatabase::class.java,
                    "note.database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }
}