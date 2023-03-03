package com.example.mornhousett.di

import android.app.Application
import androidx.room.Room
import com.example.mornhousett.data.local.FactDatabase
import com.example.mornhousett.data.remote.NumbersApi
import com.example.mornhousett.data.repository.NumberRepository
import com.example.mornhousett.other.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFactDatabase(app: Application): FactDatabase {
        return Room.databaseBuilder(
            app,
            FactDatabase::class.java,
            "fact_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNumbersApi(): NumbersApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NumbersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNumberRepository(
        api: NumbersApi,
        db: FactDatabase
    ): NumberRepository {
        return NumberRepository(
            api,
            db.factDao()
        )
    }
}