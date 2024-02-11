package com.edan.payplusapp.Service

import com.edan.payplusapp.model.BillingEntryDetails
import com.edan.payplusapp.model.BillingEntryHeader
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BillingService {
    private val billingUrl = "http://192.168.1.127:8030/payment/billing/entry/"

    private val billingAPI: BillingEntryAPI

    init {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

        billingAPI = Retrofit.Builder()
            .baseUrl(billingUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(BillingEntryAPI::class.java)
    }

    suspend fun getBillingList(): List<BillingEntryHeader> {
        return billingAPI.getBillingList().headers.sortedBy { it.entryNumber }
    }

    suspend fun getBillingDetails(id: Long): BillingEntryDetails {
        return billingAPI.getBillingDetails(BillingEntryDetailsRequest(id)).details
    }

    suspend fun deleteBillingEntry(id: Long): Int {
        return billingAPI.deleteBillingEntry(id)
    }


}