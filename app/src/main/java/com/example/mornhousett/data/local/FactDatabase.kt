package com.example.mornhousett.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mornhousett.model.Fact

@Database(
    entities = [Fact::class],
    version = 1
)
abstract class FactDatabase: RoomDatabase() {

    abstract fun factDao(): FactDao

}