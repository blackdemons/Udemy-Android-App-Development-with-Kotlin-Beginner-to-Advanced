package com.vid.noteapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val description: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

}