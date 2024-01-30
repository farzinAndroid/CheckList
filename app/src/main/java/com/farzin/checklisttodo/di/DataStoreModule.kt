package com.farzin.checklisttodo.di

import android.content.Context
import com.farzin.checklisttodo.data.datastore.DataStoreRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {


    @Provides
    @Singleton
    fun provideDataStoreImpl(@ApplicationContext context:Context) = DataStoreRepositoryImpl(context)

}