package com.impact.assistantapp.data.model

data class Plan(
    val id: String,
    val name: String,
    val description: String,
    val time: String,
    val date: String,
    val isCompleted: Boolean
)