package com.example.android.notepadapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class notes (@ColumnInfo(name = "note")val text: String){
    @PrimaryKey(autoGenerate = true)var id=0
}