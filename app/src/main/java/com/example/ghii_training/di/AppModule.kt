package com.example.ghii_training.di

import android.content.Context
import androidx.room.Room
import com.example.ghii_training.data.local.Database
import com.example.ghii_training.data.local.repo.RepoRepositoryImpl
import com.example.ghii_training.data.local.user.UserRepositoryImpl
import com.example.ghii_training.data.remote.RemoteSource
import com.example.ghii_training.domain.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //Database
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): Database {
        return Room.databaseBuilder(context, Database::class.java, Database.DATABASE_NAME).build()
    }

    //Local Repo
    @Provides
    fun providesRepository(
        database: Database,
        api: RemoteSource
    ) = BaseRepository(
       UserRepositoryImpl(database, api),
         RepoRepositoryImpl(api)
    )

    //Remote Repo
    @Provides
    @Singleton
    fun provideRemoteSource(): RemoteSource = Retrofit.Builder()
        .baseUrl(RemoteSource.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RemoteSource::class.java)
}