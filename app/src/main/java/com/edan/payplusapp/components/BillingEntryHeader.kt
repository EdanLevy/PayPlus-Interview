package com.edan.payplusapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.edan.payplusapp.R
import com.edan.payplusapp.model.BillingEntryHeader
import com.edan.payplusapp.ui.theme.LightGray
import com.edan.payplusapp.ui.theme.MainGreen
import com.edan.payplusapp.ui.theme.ManualSourceColor
import com.edan.payplusapp.ui.theme.PosSourceColor
import com.edan.payplusapp.ui.theme.TerminalSourceColor
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date


private val billingSourceToIcon = mapOf(
    "Terminal" to R.drawable.ic_pos,
    "Card" to R.drawable.ic_card,
    "Manual" to R.drawable.ic_attendant
)

private val billingSourceToColor = mapOf(
    "Terminal" to TerminalSourceColor,
    "Card" to PosSourceColor,
    "Manual" to ManualSourceColor
)

@Composable
fun BillingEntryHeader(entryHeader: BillingEntryHeader, onClick: (BillingEntryHeader) -> Unit) {
    val datetimeFormatter = SimpleDateFormat("dd-MM-yyyy HH:mm")
    Card(
        modifier = Modifier
            .clickable { onClick(entryHeader) }
            .padding(4.dp)
            .height(50.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(
                        id = billingSourceToIcon[entryHeader.source] ?: R.drawable.ic_attendant
                    ), contentDescription = "source icon",
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            billingSourceToColor[entryHeader.source] ?: ManualSourceColor
                        )
                )
                Column {
                    Text(text = "%.2f".format(entryHeader.price))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = datetimeFormatter.format(Date(entryHeader.created)))
                        if (entryHeader.cardType.equals("MasterCard", ignoreCase = true)) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_upload),
                                contentDescription = "uplaod icon",
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(MainGreen)
                            )
                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = if (entryHeader.currencyCode.equals("USD")) R.drawable.ic_dollar else R.drawable.ic_ils),
                    contentDescription = ""
                )
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "")
            }
        }
    }
}