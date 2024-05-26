package com.farzin.checklisttodo.model.home

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.farzin.checklisttodo.utils.Constants

@Entity(tableName = Constants.TABLE_NAME)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId:Int = 0,
    val title:String = "",
    val description:String = "",
    val priority:Int = -1,
    val dueYear:Int = 0,
    val dueMonth:Int = 0,
    val dueDay:Int = 0,
    val dueTime:String = "",
    val subTask:List<Subtask> = emptyList(),
)

data class Subtask(
    val subtaskId:Int = 0,
    var title: String = "",
    val subtaskCompleted:Boolean = false,
)


