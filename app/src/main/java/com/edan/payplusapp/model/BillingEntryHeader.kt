package com.edan.payplusapp.model

import com.google.gson.annotations.SerializedName

data class BillingEntryHeader(
    @SerializedName("id") val id: Long,
    @SerializedName("price") val price: Double,
    @SerializedName("created") val created: Long,
    @SerializedName("entryNumber") val entryNumber: Int,
    @SerializedName("val") val `val`: Int,
    @SerializedName("source") val source: String,
    @SerializedName("currencyCode") val currencyCode: String,
    @SerializedName("cardType") val cardType: String
)