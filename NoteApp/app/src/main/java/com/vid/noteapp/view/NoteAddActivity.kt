package com.vid.noteapp.view

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vid.noteapp.R
import com.vid.noteapp.databinding.ActivityNoteAddBinding

class NoteAddActivity : AppCompatActivity() {
    lateinit var  binding: ActivityNoteAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteAddBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Add Note"

        binding.btnCancel.setOnClickListener{
            Toast.makeText(applicationContext, "Nothing Saved", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnSave.setOnClickListener {
            saveNote()
        }
    }

    fun saveNote(){

        val noteTitle: String = binding.etNoteTitle.text.toString()
        val noteDescription: String = binding.etNoteDescription.text.toString()

        val intent = Intent()
        intent.putExtra("title", noteTitle)
        intent.putExtra("description", noteDescription)
        setResult(RESULT_OK, intent)
        finish()

    }
}