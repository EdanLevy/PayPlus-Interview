package com.edan.payplusapp.Service

import com.edan.payplusapp.model.BillingEntryDetails
import com.edan.payplusapp.model.BillingEntryHeader
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface BillingEntryAPI {

    @POST("headers")
    suspend fun getBillingList(): BillingHeaderListResponse

    @POST("details")
    suspend fun getBillingDetails(@Body request: BillingEntryDetailsRequest): BillingEntryDetailsResponse

    @POST("delete")
    suspend fun deleteBillingEntry(id: Long): Int
}