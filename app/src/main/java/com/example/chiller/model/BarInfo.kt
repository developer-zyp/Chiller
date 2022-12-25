package com.example.chiller.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BarInfo")
data class BarInfo(
    @PrimaryKey(autoGenerate = true)
    val resid: Int,
    val address: String,
    val categoryid: Int,
    val contact: String,
    val facebook: String,
    val image: String,
    val name: String,
    val phone: String,
    val rescode: String,
    val townshipid: Int,
    val type: String,
    val website: String
) : java.io.Serializable