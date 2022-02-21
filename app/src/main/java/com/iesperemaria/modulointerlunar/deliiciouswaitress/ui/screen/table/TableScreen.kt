package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.OrderItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton.FabIcon
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton.MultiFabItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton.MultiFloatingActionButton
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.CustomSwipeToDismiss
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun TableScreen(
    navController: NavController,
    tableViewModel: TableViewModel
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.table) + " " + tableViewModel.table.value.id,
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                navController = navController,
                onButtonClicked = { navController.popBackStack() }
            )
        },
        content = {
            TableContent(
                tableViewModel = tableViewModel,
                navController = navController
            )
        },
        floatingActionButton = {
            TableFAB(
                navController,
                tableViewModel
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTableScreen() {
    DeliiciousWaitressTheme {
        TableScreen(
            navController = rememberNavController(),
            tableViewModel = TableViewModel()
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TableContent(tableViewModel: TableViewModel, navController: NavController) {
    val table = tableViewModel.table.value

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = stringResource(id = R.string.orders_list),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textDecoration = TextDecoration.Underline
            )

            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(0.9f)
            ) {
                items(
                    table.actualTicket?.orders?.toList() ?: emptyList(),
                    { it.id }
                ) { order ->
                    val dismissState = rememberDismissState()
                    CustomSwipeToDismiss(
                        swipeAction = {
                            tableViewModel.deleteOrder(order)
                        },
                        dismissState = dismissState
                    ) {
                        OrderItem(
                            order = order,
                            dismissState = dismissState
                        ) {
                            tableViewModel.deleteOrder(order)
                        }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            if (table.actualTicket != null && table.actualTicket!!.orders.size > 0)
            Button(
                onClick = { tableViewModel.loadPaymentScreen(navController) },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight()
                    .padding(bottom = 10.dp)
            ) {
                Text(text = "Pedir Cuenta")
            }
        }
    }
}

@Composable
fun TableFAB(navController: NavController, tableViewModel: TableViewModel) {
    MultiFloatingActionButton(
        items = listOf(
            MultiFabItem(
                id = 1,
                imageVector = Icons.Filled.Add,
                onClick = {
                    val ticket = tableViewModel.table.value.actualTicket
                    if (ticket == null)
                        tableViewModel.createTicket(tableViewModel.table.value)

                    tableViewModel.loadDishSelectorScreen(navController)
                }
            ),
            MultiFabItem(
                id = 2,
                imageVector = Icons.Filled.Delete,
                onClick = {
                    /*
                    if(tableViewModel.table.value.actualTicket?.orders == null || tableViewModel.table.value.actualTicket?.orders!!.size == 0)
                        tableViewModel.deleteTable(navController)
                    else
                        Toast.makeText(navController.context, "Error, la mesa tiene un ticket activo", Toast.LENGTH_SHORT).show()
                     */
                    /* TODO */
                }
            )
        ),
        fabIcon = FabIcon()
    )
}