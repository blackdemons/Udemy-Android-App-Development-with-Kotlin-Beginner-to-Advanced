package com.vid.noteapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vid.noteapp.databinding.NoteItemBinding
import com.vid.noteapp.model.Note
import com.vid.noteapp.view.MainActivity
import com.vid.noteapp.view.NoteEditActivity

class NoteAdapter(private val activity: MainActivity) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var notes: List<Note> = ArrayList()
    class NoteViewHolder(binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val tvTitle: TextView = binding.tvTitle
        val tvDescriptionL: TextView = binding.tvDescription
        val cardView: CardView = binding.cardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: NoteItemBinding =
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote: Note = notes[position]
        holder.tvTitle.text = currentNote.title
        holder.tvDescriptionL.text = currentNote.description
        holder.cardView.setOnClickListener {

            val intent = Intent(activity, NoteEditActivity::class.java)
            intent.putExtra("currentTitle", currentNote.title)
            intent.putExtra("currentDescription", currentNote.description)
            intent.putExtra("currentId", currentNote.id)
            //activity result launcher
            activity.editActivityResultLauncher.launch(intent)

        }
    }

    override fun getItemCount(): Int {
        return  notes.size
    }

    fun setNote(myNote: List<Note>){
        this.notes = myNote
        notifyDataSetChanged()
    }
    fun getNote(position: Int): Note{
        return notes[position]
    }
}