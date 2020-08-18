package com.impact.assistantapp.data.model.finance


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("data")
    val _data: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
) {
    data class Data(
        @SerializedName("EURRUB")
        var euroToRuble: String,
        @SerializedName("USDRUB")
        var dollarToRuble: String
    )
}