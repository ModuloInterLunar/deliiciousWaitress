package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayContent
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun TicketListItem(
    ticket: Ticket
){
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = colorResource(id = R.color.white_2),
        elevation = 8.dp
    ) {
        Column() {
            Row() {
                Text(
                    text = ticket.orders[0].table,
                    modifier = Modifier
                        .padding(5.dp)

                )
                Text(
                    text = ticket.totalFormatted(),
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                )
            }
            Text(
                modifier = Modifier
                    .padding(5.dp),
                text = ticket.createdAt
            )
        }
    }
}

@Preview
@Composable
fun TicketListItemPreview() {
    TicketListItem(Ticket(
        id = "1",
        createdAt = "13-02-2022 a las 13:14",
        orders = mutableListOf(Order(table = "5")),
        total = 26.52
    ))
}