package com.example.android.notepadapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(notes::class), version = 1, exportSchema = false)
abstract class notesRoomDtabase : RoomDatabase(){
    abstract  fun  GetnotesDao():notesDao
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: notesRoomDtabase? = null

        fun getDatabase(context: Context): notesRoomDtabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    notesRoomDtabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}