package com.example.android.notepadapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class notesRepositories(private val notesDao: notesDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.

    val allnotes: LiveData<List<notes>> = notesDao.fetch()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(note: notes) {
        notesDao.insert(note)
    }
    suspend fun del(note: notes) {
        notesDao.del(note)
    }

}