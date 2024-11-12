package com.example.mobiledeveloping.appinfo.di

import android.app.Application
import com.example.appinfo.db.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.injext.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb{
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "info.db"
        ).createFromAsset("db/info.db").build()
    }

}