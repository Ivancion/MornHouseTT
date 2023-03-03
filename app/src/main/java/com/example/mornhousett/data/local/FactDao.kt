package com.example.mornhousett.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mornhousett.model.Fact
import kotlinx.coroutines.flow.Flow

@Dao
interface FactDao {

    @Insert
    suspend fun insertFact(fact: Fact)

    @Query("SELECT * FROM fact_table")
    fun observeAllFacts(): Flow<List<Fact>>

    @Query("SELECT * FROM fact_table WHERE id = :id")
    suspend fun getFactById(id: Int): Fact
}