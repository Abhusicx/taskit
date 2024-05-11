package com.example.taskit.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
abstract class TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addTask(taskEntity: Task)

    //Load all tasks from the wish table
    @Query("Select * from `task-table`")
    abstract fun getAllTasks(): Flow<List<Task>>

    @Update
    abstract fun updateTask(taskEntity: Task)

    @Delete
    abstract fun deleteTask(taskEntity: Task)

    @Query("Select * from `task-table` where id=:id")
    abstract fun getATaskById(id:Long):Flow<Task>


}