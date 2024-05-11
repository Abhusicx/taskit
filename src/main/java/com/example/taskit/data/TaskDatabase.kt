package com.example.taskit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskit.data.Task
import com.example.taskit.data.TaskDao


@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)

abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}