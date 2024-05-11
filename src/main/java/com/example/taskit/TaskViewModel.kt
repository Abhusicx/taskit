package com.example.taskit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskit.data.Task
import com.example.taskit.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class TaskViewModel(
    private val taskRepository: TaskRepository = Graph.taskRepository
):ViewModel() {

    var taskTitleState by mutableStateOf("")
    var taskDescriptionState by mutableStateOf("")


    fun onTaskTitleChanged(newString: String){
        taskTitleState = newString
    }

    fun onTaskDescriptionChanged(newString: String){
        taskDescriptionState = newString
    }

    lateinit var getAllTasks: Flow<List<Task>>

    init {
        viewModelScope.launch {
            getAllTasks = taskRepository.getTask()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(task=task)
        }
    }

    fun getATaskById(id: Long): Flow<Task>{
        return taskRepository.getATaskbyId(id)
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.updateATask(task=task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.deleteATask(task=task)
        }
    }
}