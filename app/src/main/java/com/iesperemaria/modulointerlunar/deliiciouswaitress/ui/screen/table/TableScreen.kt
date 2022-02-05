package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@Composable
fun TableScreen(
    navController: NavController,
    tableViewModel: TableViewModel
){
    ViewContainer(tableViewModel = tableViewModel)
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

@Composable
fun ViewContainer(tableViewModel: TableViewModel){
    Scaffold(
        floatingActionButton = {
            FAB(
                tableViewModel = tableViewModel
            )
        },
        content = {
            Content(
                tableViewModel = tableViewModel
            )
        }
    )
}

@Composable
fun Content(tableViewModel: TableViewModel){
    val table = tableViewModel.table.value

    Surface{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = "${stringResource(id = R.string.selected_table)} ${table.id}",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = stringResource(id = R.string.orders_list),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textDecoration = TextDecoration.Underline
            )

            LazyColumn  {
                itemsIndexed(
                    items = table.actualTicket?.orders?: emptyList()
                ) {
                        index, table -> Text(text = "$index. ${table.dish.name}")
                }
            }
        }
    }
}

@Composable
fun FAB(tableViewModel: TableViewModel) {
    val ticket = tableViewModel.table.value.actualTicket

    if(ticket == null)
        tableViewModel.createTicket()

    FloatingActionButton(
        onClick = { /*TODO*/ },
    ) {
        Text("+")
    }
}