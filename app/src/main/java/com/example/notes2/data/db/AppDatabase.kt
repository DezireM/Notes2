package com.example.notes2.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.notes2.data.db.daos.NoteDao
import com.example.notes2.data.models.NoteModel

@Database(entities = [NoteModel::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}