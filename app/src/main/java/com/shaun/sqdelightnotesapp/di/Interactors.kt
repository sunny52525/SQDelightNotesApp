package com.shaun.sqdelightnotesapp.di

import android.content.Context
import com.shaun.sqdelightnotesapp.NotesApp
import com.shaun.sqdelightnotesapp.presentation.screens.mainscreen.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object Interactors {

    @Singleton
    @Provides
    fun provideMainRepo(
        context: NotesApp
    )=MainRepository(context = context)
}