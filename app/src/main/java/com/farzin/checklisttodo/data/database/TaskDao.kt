package com.farzin.checklisttodo.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.farzin.checklisttodo.model.home.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {


    @Query("select * from task order by taskId desc")
    fun getAllTasks() : Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("select * from task where taskId = :taskId")
    fun getSingleTask(taskId:Int) : Flow<Task>

    @Query("select * from task where title like '%' ||:query|| '%'")
    fun searchTasks(query:String) : List<Task>

    @Query("select * from task order by priority desc")
    fun sortedTasksPriorityDesc() : Flow<List<Task>>


    @Query("select * from task order by priority asc")
    fun sortedTasksPriorityAsc() : Flow<List<Task>>

}