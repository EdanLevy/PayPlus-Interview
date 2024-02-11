package com.edan.payplusapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.edan.payplusapp.BillingViewModel
import com.edan.payplusapp.R
import com.edan.payplusapp.components.billingSourceToColor
import com.edan.payplusapp.components.billingSourceToIcon
import com.edan.payplusapp.ui.theme.LightGray
import com.edan.payplusapp.ui.theme.MainGreen
import com.edan.payplusapp.ui.theme.ManualSourceColor
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun BillingDetailScreen(viewModel: BillingViewModel, onDelete: () -> Unit = {}) {
    val datetimeFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
    val details by remember {
        viewModel.billingDetailsState
    }
    if (details != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {// details header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                //source type
                Card(
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .background(billingSourceToColor[details?.source] ?: ManualSourceColor)
                ) {
                    Image(
                        painter = painterResource(
                            id = billingSourceToIcon[details?.source] ?: R.drawable.ic_attendant
                        ),
                        contentDescription = "source icon",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                    )
                }

                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "%.2f".format(details?.price), fontSize = 32.sp,
                            color = if (details!!.status.equals("Passed")) MainGreen else Color.Red
                        )
                        Icon(
                            painter = painterResource(id = if (details?.currencyCode.equals("USD")) R.drawable.ic_dollar else R.drawable.ic_ils),
                            contentDescription = "currency icon"
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = datetimeFormatter.format(Date(details!!.created)),
                            fontSize = 24.sp
                        )
                        Text(text = details!!.entryNumber.toString())
                    }

                    Divider(thickness = 2.dp, color = LightGray)
                }
            }

            // details info
            details?.let {
                EntryDetailInfo(title = "Status", value = it.status)
                EntryDetailInfo(title = "Card Number", value = "****${it.cardNumber.substring(4)}")
                EntryDetailInfo(title = "Card Type", value = it.cardType)
                EntryDetailInfo(title = "Issuer", value = it.issuer)
                EntryDetailInfo(title = "Source", value = it.source)
                EntryDetailInfo(title = "Terminal Name", value = it.terminalName)

                Divider(thickness = 2.dp, color = LightGray)

                EntryDetailInfo(title = "Total Paid", value = "%.2f".format(it.amountPaid))
                EntryDetailInfo(
                    title = "Remaining Amount",
                    value = "%.2f".format(it.price - it.amountPaid)
                )

                EntryDetailInfo(title = "Approval Number", value = it.approvalNumber)
                EntryDetailInfo(title = "Voucher Number", value = it.voucherNumber)
            }

            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red), onClick = {
                viewModel.deleteBillingEntry(details!!.id)
                onDelete()

            }) {
                Icon(Icons.Default.Delete, contentDescription = "delete button")
            }
        }


    } else {
        Box(
            modifier = Modifier
                .fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Loading...")
            }
        }
    }
}

@Composable
fun EntryDetailInfo(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, fontSize = 20.sp)
        Text(text = value, fontSize = 22.sp)
    }
}
