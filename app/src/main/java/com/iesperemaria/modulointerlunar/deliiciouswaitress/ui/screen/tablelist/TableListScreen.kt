package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.*
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar
import kotlin.math.absoluteValue

@Composable
fun TableListScreen(
    navController: NavController,
    tableListViewModel: TableListViewModel,
    openDrawer: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.tables),
                buttonIcon = painterResource(id = R.drawable.hamburger_icon),
                onButtonClicked = { openDrawer() }
            )
        },
        content = {
            TableListContent(
                navController = navController,
                tableListViewModel = tableListViewModel
            )
        },
        floatingActionButton = {
            TableListFAB(
                tableListViewModel = tableListViewModel
            )
        }
    )
}

@Composable
fun TableListFAB(tableListViewModel: TableListViewModel) {
    FloatingActionButton(
        onClick = {
            tableListViewModel.createTable(Table())

        },
    ) {
        Text("+")
    }
}

@Composable
fun TableListContent(navController: NavController, tableListViewModel: TableListViewModel) {
    var size by remember { mutableStateOf(IntSize.Zero) }

    val tables = tableListViewModel.tables.value
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .onGloballyPositioned { size = it.size }
                    /*.then(
                        with(LocalDensity.current) {
                            Modifier.size(
                                width = size.width.toDp(),
                                height = size.height.toDp()
                            )
                        }
                    )*/
            ) {
                Image(
                    painter = painterResource(id = R.drawable.restaurante),
                    contentDescription = "",
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End)
                        .fillMaxSize()
                )
                tables.forEach { table ->
                    TableListItem(
                        navController = navController,
                        tableListViewModel,
                        table.id,
                        parentSize = size
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    DeliiciousWaitressTheme {
        TableListScreen(
            navController = rememberNavController(),
            tableListViewModel = TableListViewModel(),
            openDrawer = {

            }
        )
    }
}