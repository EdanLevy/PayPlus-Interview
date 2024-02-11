package com.edan.payplusapp.model

import com.google.gson.annotations.SerializedName

data class BillingEntryDetails(
    @SerializedName("id") val id: Long,
    @SerializedName("price") val price: Double,
    @SerializedName("created") val created: Long,
    @SerializedName("entryNumber") val entryNumber: Int,
    @SerializedName("totalEntryCount") val totalEntryCount: Int,
    @SerializedName("currencyCode") val currencyCode: String,
    @SerializedName("amountPaid") val amountPaid: Double,
    @SerializedName("status") val status: String,
    @SerializedName("cardNumber") val cardNumber: String,
    @SerializedName("cardType") val cardType: String,
    @SerializedName("issuer") val issuer: String,
    @SerializedName("source") val source: String,
    @SerializedName("terminalNumber") val terminalNumber: String,
    @SerializedName("approvelNumber") val approvelNumber: String,
    @SerializedName("voucherNumber") val voucherNumber: String
)
