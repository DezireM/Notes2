package com.example.notes2.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notes2.data.models.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteModel")
    fun getAll(): LiveData<List<NoteModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteModel: NoteModel)

    @Delete
    fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * FROM noteModel WHERE id = :id")
    fun getNoteById(id: Int): NoteModel?

    @Update
    fun updateNote(noteModel: NoteModel)

}