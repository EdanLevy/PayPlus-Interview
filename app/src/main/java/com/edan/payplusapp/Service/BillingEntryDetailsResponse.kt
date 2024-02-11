package com.edan.payplusapp.Service

import com.edan.payplusapp.model.BillingEntryDetails
import com.google.gson.annotations.SerializedName

data class BillingEntryDetailsResponse(@SerializedName("details") val details: BillingEntryDetails)
