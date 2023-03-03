package com.example.mornhousett.data.repository

import com.example.mornhousett.data.local.FactDao
import com.example.mornhousett.data.remote.NumbersApi
import com.example.mornhousett.model.Fact
import com.example.mornhousett.other.Constants
import com.example.mornhousett.other.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class NumberRepository(
    private val api: NumbersApi,
    private val factDao: FactDao
) {

    suspend fun getRandomFact(): Flow<Resource<Unit>> {
        return flow {
            runCatching {
                api.getRandomFact()
            }.onSuccess {
                factDao.insertFact(it)
                emit(Resource.Success(Unit))
            }.onFailure {
                emit(Resource.Error(it.localizedMessage ?: it.message ?: Constants.UNKNOWN_ERROR_MESSAGE))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFactByNumber(number: Int): Flow<Resource<Unit>> {
        return flow {
            runCatching {
                api.getFactByNumber(number)
            }.onSuccess {
                factDao.insertFact(it)
                emit(Resource.Success(Unit))
            }.onFailure {
                emit(Resource.Error(it.localizedMessage ?: it.message ?: Constants.UNKNOWN_ERROR_MESSAGE))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFactById(id: Int) = withContext(Dispatchers.IO) {
        factDao.getFactById(id)
    }

    fun observeAllFacts(): Flow<List<Fact>> {
        return factDao.observeAllFacts()
    }

}