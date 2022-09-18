package com.vid.noteapp

import android.app.Application
import com.vid.noteapp.repository.NoteRepository
import com.vid.noteapp.room.NoteDatebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NoteApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NoteDatebase.getDatabase(this, applicationScope) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }
}