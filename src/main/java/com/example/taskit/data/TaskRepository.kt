package com.example.taskit.data

import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }

    fun getTask(): Flow<List<Task>> = taskDao.getAllTasks()

    fun getATaskbyId(id:Long) : Flow<Task>{
        return taskDao.getATaskById(id)
    }

    suspend fun updateATask(task: Task){
        taskDao.updateTask(task)
    }

    suspend fun deleteATask(task: Task){
        taskDao.deleteTask(task)
    }
}