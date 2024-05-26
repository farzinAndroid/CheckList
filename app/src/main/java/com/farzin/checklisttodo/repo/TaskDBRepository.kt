package com.farzin.checklisttodo.repo

import com.farzin.checklisttodo.data.database.TaskDao
import com.farzin.checklisttodo.model.home.Task
import javax.inject.Inject

class TaskDBRepository @Inject constructor(private val dao: TaskDao) {


    val allTasks = dao.getAllTasks()

    suspend fun insertTask(task: Task) = dao.insertTask(task)

    suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    suspend fun updateTask(task: Task) = dao.updateTask(task)

    fun getSingleTask(taskId: Int) = dao.getSingleTask(taskId)

    fun searchTasks(query:String) = dao.searchTasks(query)

    fun sortedTasksPriorityDesc() = dao.sortedTasksPriorityDesc()

    fun sortedTasksPriorityAsc() = dao.sortedTasksPriorityAsc()




}