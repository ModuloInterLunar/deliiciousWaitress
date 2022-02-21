package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray


import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun OutputTrayScreen(
    navController: NavController,
    outputTrayViewModel: OutputTrayViewModel,
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
            OutputTrayContent(
                outputTrayViewModel = outputTrayViewModel
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOutputTrayScreen() {
    DeliiciousWaitressTheme {
        OutputTrayScreen(
            navController = rememberNavController(),
            outputTrayViewModel = OutputTrayViewModel(Application()),
        )
        {}
    }
}

@Composable
fun OutputTrayContent(outputTrayViewModel: OutputTrayViewModel) {
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

            LazyColumn(modifier = Modifier.padding(10.dp)) {
                items(
                    outputTrayViewModel.orders, {it.id}
                ) { order ->
                    OrderItem(order = order, imageId = R.drawable.checkmark) {
                        outputTrayViewModel.setServed(order)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}