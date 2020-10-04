package com.impact.assistantapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
data class Plan(
    val name: String,
    val description: String,
    val time: String,
    var date: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean
) {
    @PrimaryKey
    val id: Long? = null
}