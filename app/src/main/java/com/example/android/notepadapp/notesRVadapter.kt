package com.example.android.notepadapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class notesRVadapter(private val context: Context, private val listener: inoteRVadapter):RecyclerView.Adapter<notesRVadapter.notesViewHolder>() {

    val allnotes = ArrayList<notes>()

    inner class notesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView= itemView.findViewById<TextView>(R.id.texts)
        val delbutton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        val viewHolder=notesViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false))
        viewHolder.delbutton.setOnClickListener{
            listener.onItemClicked(allnotes[viewHolder.adapterPosition])
        }
        viewHolder.textView
        return viewHolder

    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {
       val currentnote = allnotes[position]
        holder.textView.text = currentnote.text
    }

    override fun getItemCount(): Int {
      return  allnotes.size
    }
    fun updatelist(newList: List<notes>){
        allnotes.clear()
        allnotes.addAll(newList)

        notifyDataSetChanged()

    }
}
interface inoteRVadapter{
    fun onItemClicked(note:notes)
}