package com.edan.payplusapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edan.payplusapp.Service.BillingService
import com.edan.payplusapp.model.BillingEntryDetails
import com.edan.payplusapp.model.BillingEntryHeader
import kotlinx.coroutines.launch

class BillingViewModel : ViewModel() {

    val billingService: BillingService = BillingService()
    val billingEntries: MutableState<List<BillingEntryHeader>> =
        mutableStateOf(listOf())
    val billingDetailsState: MutableState<BillingEntryDetails?> = mutableStateOf(null)


    suspend fun getBillingList(): Unit {
        billingEntries.value = billingService.getBillingList()
    }

    suspend fun getBillingDetails(id: Long): Unit {
        billingDetailsState.value = billingService.getBillingDetails(id)
    }

    fun deleteBillingEntry(id: Long): Unit {
        viewModelScope.launch {
            billingService.deleteBillingEntry(id)
        }
    }

    fun onHeaderClicked(entryHeader: BillingEntryHeader): Unit {
        viewModelScope.launch { getBillingDetails(entryHeader.id) }
    }
}