package com.example.android.notepadapp

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), inoteRVadapter {
    lateinit var  viewModel : notesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //creating the recycler view
        val recycle = findViewById<RecyclerView>(R.id.recyclerView)
        recycle.layoutManager = LinearLayoutManager(this)
        val adapter = notesRVadapter(this,this)
        recycle.adapter=adapter
         // viewmodel
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(notesViewModel :: class.java)
        viewModel.allnotes.observe(this, Observer {List->
            List?.let{
                adapter.updatelist(it)
            }

        })

    }

    override fun onItemClicked(note: notes) {
         viewModel.del(note)
        Toast.makeText(this,"${note.text}DELETED",Toast.LENGTH_SHORT).show()
    }

    fun submitText(view: View) {
        val input = findViewById<TextView>(R.id.input)
        val noteText = input.text.toString()
        //if text is empty or not and then execute
        if(noteText.isNotEmpty())
        {
            viewModel.insert(notes(noteText))
        }
        Toast.makeText(this,"$noteText INSERTED",Toast.LENGTH_SHORT).show()
        input.setText("");
    }
}