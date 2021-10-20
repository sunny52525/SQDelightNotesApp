package com.shaun.sqdelightnotesapp.presentation.screens.mainscreen

import android.content.Context
import com.shaun.Database
import com.shaun.sqdelightnotesapp.Notes
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val context: Context
) {
    private val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, context, "test.db")

    private val database = Database(driver = driver)
    private val notesQuery = database.notesQueries

    fun addNotes(title: String, body: String, color: Long) {

        notesQuery.insertNotes(title = title, background_color = color, body = body)

    }
    fun getNotes(): Flow<List<Notes>> {
        return notesQuery.selectAll().asFlow().mapToList()
    }

    fun deleteNote(id:Long){
        notesQuery.deleteById(id)
    }
}