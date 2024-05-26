package com.farzin.checklisttodo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.farzin.checklisttodo.model.home.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TaskDataBase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}