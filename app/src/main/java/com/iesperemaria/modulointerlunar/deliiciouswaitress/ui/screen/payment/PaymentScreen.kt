package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun PaymentScreen(
    navController: NavController, 
    paymentViewModel: PaymentViewModel
){
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.ticket),
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                onButtonClicked = { navController.popBackStack() }
            )
        },
        content = {
            PaymentContent(navController, paymentViewModel)
        }
    ) 
}

@Composable
fun PaymentContent(
    navController: NavController,
    paymentViewModel: PaymentViewModel
){
    Column {
        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
        )

        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "C. CONCEPTO",
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                text = "PRECIO"
            )
            Text(
                text = "IMPORTE",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        }

        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.7f)
                .padding(5.dp)
        ) {
            itemsIndexed(
                items = paymentViewModel.table.value.actualTicket?.orders ?: emptyList()
            ) { index, order ->
                Row(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = "${index + 1}. ${order.dish.name}",
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                    Text(
                        text = order.dish.formatedPrice()
                    )
                    Text(
                        text = "IMPORTE",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                }
            }
        }

        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        
        Text(
            text = "Base imponible\nI.V.A 10,00%",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        val ticket = paymentViewModel.table.value.actualTicket
        ticket?.setTotal()

        Text(
            text = "Total: ${ticket?.totalFormatted() ?: ""}",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Divider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Button(
            onClick = {
                paymentViewModel.removeTicketFromTable()
                navController.navigate("table_list_screen")
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 10.dp)
        ) {
            Text(text = "Cobrar")
        }
    }
}

@Preview
@Composable
fun PaymentScreenPreview(){
    PaymentScreen(navController = rememberNavController(), paymentViewModel = PaymentViewModel())
}