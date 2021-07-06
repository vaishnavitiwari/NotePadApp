package com.example.android.notepadapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface notesDao {
// if we dont want same things to be iserted we apply conflict
     @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun insert(note: notes)
    @Delete
   suspend fun del(note: notes)
    @Query("Select * from note_table order by id ASC ")
    fun fetch():LiveData<List<notes>>
}