package com.edan.payplusapp.Service

import com.google.gson.annotations.SerializedName

data class BillingEntryDetailsRequest(
    @SerializedName("billingId") val billingId: Long)
