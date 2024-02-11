package com.edan.payplusapp.Service

import com.edan.payplusapp.model.BillingEntryHeader
import com.google.gson.annotations.SerializedName

data class BillingHeaderListResponse(@SerializedName("headers") val headers: List<BillingEntryHeader>)
