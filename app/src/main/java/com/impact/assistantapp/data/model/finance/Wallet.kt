package com.impact.assistantapp.data.model.finance

data class Wallet(
    val id: Int,
    val name: String,
    val number: String,
    val amount: String,
    val isCommon: Boolean
)