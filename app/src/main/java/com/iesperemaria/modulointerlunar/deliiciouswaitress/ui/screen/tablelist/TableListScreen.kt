package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@Composable
fun TableListScreen(
    navController: NavController,
    tableListViewModel: TableListViewModel
){
    var width by rememberSaveable { mutableStateOf( 0 ) }
    var height by rememberSaveable { mutableStateOf( 0 ) }
    var size = IntSize(width, height)

    val tables = tableListViewModel.tables.value
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Mesas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Box(
                modifier = Modifier
                    .onSizeChanged {
                        width = it.width
                        height = it.height
                    }
                    .background(Color.White)
                    .fillMaxSize()
            ){
                tables.forEach { table -> TableListItem(
                    navController = navController,
                    table = table,
                    parentSize = size
                ) }
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
            tableListViewModel = TableListViewModel()
        )
    }
}