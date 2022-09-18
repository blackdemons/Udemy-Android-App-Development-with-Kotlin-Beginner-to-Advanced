package com.vid.noteapp.repository

import androidx.annotation.WorkerThread
import com.vid.noteapp.model.Note
import com.vid.noteapp.room.NoteDAO
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDAO: NoteDAO) {

    val allMyNote: Flow<List<Note>> = noteDAO.getAllNotes()

    @WorkerThread
    suspend fun insert(note: Note){
        noteDAO.insert(note)
    }

    @WorkerThread
    suspend fun update(note: Note){
        noteDAO.update(note)
    }

    @WorkerThread
    suspend fun delete(note: Note){
        noteDAO.delete(note)
    }

    @WorkerThread
    suspend fun deleteAllNotes(){
        noteDAO.deleteAllNotes()
    }

}