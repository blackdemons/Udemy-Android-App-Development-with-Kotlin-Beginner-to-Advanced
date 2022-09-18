package com.vid.noteapp.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vid.noteapp.NoteApplication
import com.vid.noteapp.R
import com.vid.noteapp.adapters.NoteAdapter
import com.vid.noteapp.databinding.ActivityMainBinding
import com.vid.noteapp.model.Note
import com.vid.noteapp.veiwmodel.NoteViewModel
import com.vid.noteapp.veiwmodel.NoteViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel

    lateinit var addActivityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var editActivityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val noteAdapter: NoteAdapter = NoteAdapter(this)
        recyclerView.adapter = noteAdapter

        //register activity for result
        registerActivityLauncher()

        val viewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)
        noteViewModel = ViewModelProvider(this, viewModelFactory).get(NoteViewModel::class.java)

        noteViewModel.allMyNotes.observe(this, Observer { notes ->

            //update UI
            noteAdapter.setNote(notes)

        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                noteViewModel.delete(noteAdapter.getNote(viewHolder.adapterPosition))

            }

        }).attachToRecyclerView(recyclerView)
    }

    fun registerActivityLauncher() {

        addActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { resultAddNote ->

                    val resultCode = resultAddNote.resultCode
                    val data = resultAddNote.data

                    if (resultCode == RESULT_OK && data != null) {

                        val noteTitle: String = data.getStringExtra("title").toString()
                        val noteDescription: String = data.getStringExtra("description").toString()

                        val note = Note(noteTitle, noteDescription)
                        noteViewModel.insert(note)
                    }
                })
        editActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
                ActivityResultCallback { resultEditNote ->
                    val resultCode = resultEditNote.resultCode
                    val data = resultEditNote.data
                    if (resultCode == RESULT_OK && data != null){
                        val editTitle: String = data.getStringExtra("editTitle").toString()
                        val editDescription: String = data.getStringExtra("editDescription").toString()
                        val noteId = data.getIntExtra("noteId", -1)

                        val newNote = Note(editTitle, editDescription)
                        newNote.id = noteId

                        noteViewModel.update(newNote)

                    }
                })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add_note -> {
                val intent = Intent(this, NoteAddActivity::class.java)
                addActivityResultLauncher.launch(intent)
            }
            R.id.item_delete_all_note -> showDialogMessage()
        }
        return true
    }

    fun showDialogMessage(){

        val dialogMessage = AlertDialog.Builder(this)
        dialogMessage.setTitle("Delete All Notes")
        dialogMessage.setMessage("If you click Yes all notes will delete"+
                "if you want to delete specific note, please swipe left or right.")
        dialogMessage.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            dialog.cancel()
        })
        dialogMessage.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            noteViewModel.deleteAllNotes()
        })
        dialogMessage.setCancelable(false)
        dialogMessage.create().show()
    }
}