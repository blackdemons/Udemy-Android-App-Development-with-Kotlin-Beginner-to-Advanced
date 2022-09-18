package com.vid.noteapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vid.noteapp.R
import com.vid.noteapp.databinding.ActivityNoteEditBinding

class NoteEditActivity : AppCompatActivity() {
    lateinit var binding: ActivityNoteEditBinding

    var currentId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Add Note"

        getAndSetData()

        binding.btnCancelEdit.setOnClickListener{
            Toast.makeText(applicationContext, "Nothing Saved", Toast.LENGTH_SHORT).show()
            finish()
        }

        binding.btnSaveEdit.setOnClickListener {
            editNote()
        }
    }

    fun editNote(){

        val editTitle = binding.etNoteTitleEdit.text.toString()
        val editDescription = binding.etNoteDescriptionEdit.text.toString()

        val intent = Intent()

        intent.putExtra("editTitle", editTitle)
        intent.putExtra("editDescription", editDescription)
        if (currentId != -1){
            intent.putExtra("noteId", currentId)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    fun getAndSetData(){

        //get
        val currentTitle = intent.getStringExtra("currentTitle")
        val currentDescription = intent.getStringExtra("currentDescription")
        val currentId = intent.getIntExtra("currentId", -1)

        //set
        binding.etNoteTitleEdit.setText(currentTitle)
        binding.etNoteDescriptionEdit.setText(currentDescription)
    }
}