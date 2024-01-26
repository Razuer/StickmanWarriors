package com.rejzerek.stickmanwarriors.di

import android.app.Application
import androidx.room.Room
import com.rejzerek.stickmanwarriors.feature_stickman.data.data_source.StickmanDatabase
import com.rejzerek.stickmanwarriors.feature_stickman.data.repository.StickmanRepositoryImpl
import com.rejzerek.stickmanwarriors.feature_stickman.domain.repository.StickmanRepository
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.AddStickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.DeleteStickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.GetStickman
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.GetStickmen
import com.rejzerek.stickmanwarriors.feature_stickman.domain.use_case.StickmanUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStickmanDatabase(app: Application) : StickmanDatabase {
        return Room.databaseBuilder(
            app,
            StickmanDatabase::class.java,
            StickmanDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideStickmanRepository(db: StickmanDatabase) : StickmanRepository {
        return StickmanRepositoryImpl(db.stickmanDao)
    }

    @Provides
    @Singleton
    fun provideStickmanUseCases(repository: StickmanRepository): StickmanUseCases {
        return StickmanUseCases(
            getStickmen = GetStickmen(repository),
            deleteStickman = DeleteStickman(repository),
            addStickman = AddStickman(repository),
            getStickman = GetStickman(repository)
        )
    }
}