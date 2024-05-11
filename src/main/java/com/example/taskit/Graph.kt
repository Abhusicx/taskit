package com.example.taskit

import android.content.Context
import androidx.room.Room
import com.example.taskit.data.TaskDatabase
import com.example.taskit.data.TaskRepository

object Graph {
    lateinit var database: TaskDatabase

    val taskRepository by lazy {
        TaskRepository(taskDao = database.taskDao())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context, TaskDatabase::class.java, "taskList.db").build()
    }


}