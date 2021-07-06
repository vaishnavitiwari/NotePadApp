package com.example.android.notepadapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class notesViewModel(application: Application):AndroidViewModel(
    application
){
    private val repository :notesRepositories
    val allnotes : LiveData<List<notes>>
    init{
     val dao = notesRoomDtabase.getDatabase(application).GetnotesDao()
         repository = notesRepositories(dao)
        allnotes = repository.allnotes

    }
    //we have to again define delete function in this view model as main activity
    // ,since del is suspend function so it can only be called from a co routine or another suspend function ,
    // hence we have to call viewmodelscope and then  del fun in that scope
  fun del(note:notes)=viewModelScope.launch(Dispatchers.IO){
     repository.del(note)
  }
fun insert(note:notes)=viewModelScope.launch(Dispatchers.IO){
    repository.insert(note)
}
}