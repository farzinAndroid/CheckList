package com.farzin.checklisttodo.di

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import com.farzin.checklisttodo.data.alarm.AlarmReceiver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AlarmModule {

    @Provides
    @Singleton
    fun provideAlarmManager(@ApplicationContext context:Context) =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @Provides
    @Singleton
    fun provideAlarmIntent(@ApplicationContext context: Context) =
        Intent(context, AlarmReceiver::class.java)

}