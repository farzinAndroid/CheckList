package com.farzin.checklisttodo.di

import android.content.Context
import androidx.room.Room
import com.farzin.checklisttodo.data.database.TaskDataBase
import com.farzin.checklisttodo.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskDBModule {


    @Provides
    @Singleton
    fun provideDB(@ApplicationContext context:Context) =
        Room
            .databaseBuilder(
                context,TaskDataBase::class.java,name = Constants.DB_NAME)
            .build()

    @Provides
    @Singleton
    fun provideTaskDao(taskDataBase: TaskDataBase) = taskDataBase.taskDao()


}