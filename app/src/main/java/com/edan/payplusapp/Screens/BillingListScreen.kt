package com.edan.payplusapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edan.payplusapp.BillingViewModel
import com.edan.payplusapp.components.BillingEntryHeader
import com.edan.payplusapp.model.BillingEntryHeader


@Composable
fun BillingListScreen(viewModel: BillingViewModel, onClick: (BillingEntryHeader) -> Unit) {
    val entries by remember { viewModel.billingEntries }
    if (entries.isEmpty()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = "Loading...")
        }
    } else {
        LazyColumn() {
            items(entries) { entry: BillingEntryHeader ->
                BillingEntryHeader(entryHeader = entry, onClick = onClick)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.getBillingList()
    }
}
