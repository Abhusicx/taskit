package com.example.taskit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="task-table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "task-title")
    val title: String = "",
    @ColumnInfo(name = "task-desc")
    val description: String = ""
)

object DummyTask{
    val taskList = listOf(
        Task(title = "Mom", description = "To keep her happy"),

        Task(title = "settled Job", description = "A job that i could settle with"),

        Task(title = "Music", description = "A home Studio"),

        Task(title = "Badminton", description = "To represent Country in badminton")
    )
}
