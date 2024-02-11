package com.edan.payplusapp.Screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.edan.payplusapp.BillingViewModel

@Composable
fun BillingDetailScreen(viewModel: BillingViewModel) {

    val details by remember {
        viewModel.billingDetailsState
    }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        if (details == null) {
            Text("loading billing details...")
        } else {

            Text(text = "billing details for entry: ${details?.id}")

        }
    }
}