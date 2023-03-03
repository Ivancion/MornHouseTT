package com.example.mornhousett.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fact_table")
data class Fact(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("text")
    val text: String,
    @SerializedName("number")
    val number: Int
)



