package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun TicketListScreen (
    navController: NavController,
    ticketListViewModel: TicketListViewModel,
    openDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.output_tray),
                buttonIcon = painterResource(id = R.drawable.hamburger_icon),
                navController = navController,
                onButtonClicked = { openDrawer() }
            )
        },
        content = {
            TicketListContent(
                navController = navController,
                ticketListViewModel = ticketListViewModel
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TicketListContent (
    navController: NavController,
    ticketListViewModel: TicketListViewModel
) {
    val tickets = ticketListViewModel.tickets.value

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Text(
                text = stringResource(id = R.string.list_of_paid_tickets),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textDecoration = TextDecoration.Underline
            )

            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 172.dp)
            ) {
                items(tickets) { ticket ->
                    TicketListItem(ticket = ticket) {
                        navController.navigate(AppScreens.PaymentScreen.route + "/${ticket.id}")
                    }
                }
            }
        }
    }



}

