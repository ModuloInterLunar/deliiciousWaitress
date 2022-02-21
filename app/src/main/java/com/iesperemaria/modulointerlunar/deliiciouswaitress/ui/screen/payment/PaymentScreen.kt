package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.format

const val TAG = "payment_screen"

@Composable
fun PaymentScreen(
    navController: NavController,
    paymentViewModel: PaymentViewModel
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.ticket),
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                navController = navController,
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
) {
    val ticket = paymentViewModel.ticket.value
    ticket.setTotal()
    val groupedOrders = ticket.orders.groupBy { it.dish.id }
    val groupedOrderValues = groupedOrders.values.toList()
    val context = LocalContext.current
    val dividerColor =
        if (isSystemInDarkTheme()) colorResource(id = R.color.lighter_grey) else Color.Black

    Log.d(TAG, "[${groupedOrderValues.joinToString(",")}]")

    Column {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.ticket_title_1),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 25.sp
                )
                Text(
                    text = stringResource(id = R.string.ticket_title_2),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 35.sp
                )
            }
        }

        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = ticket.date()
            )

            Text(
                text = ticket.hour(),
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )

        }
        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
        )

        Row(
            modifier = Modifier.padding(5.dp)
        ) {
            Text(
                text = "C. CONCEPTO",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(0.6f)
            )
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "PRECIO"
            )
            Text(
                fontWeight = FontWeight.SemiBold,
                text = "IMPORTE",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Right
            )
        }

        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .padding(5.dp)
        ) {
            itemsIndexed(
                items = groupedOrderValues
            ) { index, groupedOrders ->
                val order = groupedOrders[0]
                Row(
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = "${groupedOrders.size} x ${order.dish.name}",
                        modifier = Modifier.fillMaxWidth(0.6f)
                    )
                    Text(
                        text = order.dish.formatedPrice()
                    )
                    Text(
                        text = "${(order.dish.price * groupedOrders.size).format(2)} €",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Right
                    )
                }
            }
        }

        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Base imponible:\nI.V.A 10,00%:",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(10.dp)
            )
            Text(
                text = ticket.totalNoIvaFormatted() + "\n" + ticket.ivaFormatted(),
                modifier = Modifier
                    .padding(10.dp)
            )

        }

        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 5.dp)
        )


        Text(
            fontWeight = FontWeight.SemiBold,
            text = "Total: ${ticket.totalFormatted()}",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )

        Divider(
            color = dividerColor,
            thickness = 1.dp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Text(
            fontWeight = FontWeight.SemiBold,
            text = "Atendido por: ${if (ticket.orders.isNotEmpty()) ticket.orders[0].employee.fullName() else ""}",
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Right
        )


        if (!ticket.isPaid)
            Button(
                onClick = {
                    paymentViewModel.removeTicketFromTable()
                    Toast.makeText(
                        context,
                        context.getString(R.string.ticket_paid),
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(AppScreens.TableListScreen.route) {
                        popUpTo(AppScreens.TableListScreen.route)
                    }
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
fun PaymentScreenPreview() {
    PaymentScreen(navController = rememberNavController(), paymentViewModel = PaymentViewModel())
}