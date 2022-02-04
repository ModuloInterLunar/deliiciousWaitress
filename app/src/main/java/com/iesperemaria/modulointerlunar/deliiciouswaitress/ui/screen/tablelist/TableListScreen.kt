package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.graphics.Point
import android.util.Size
import android.view.Surface
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.ParentDataModifier
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@Composable
fun TableListScreen(
    navController: NavController,
    tableModelList: List<Table>
){
    var size = IntSize(0, 0)

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
                    .onSizeChanged { size = it }
                    .background(Color.White)
                    .fillMaxSize()
            ){
                tableModelList.forEach { table -> TableItem(
                    navController = navController,
                    table = table,
                    id = table.id,
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
            tableModelList = emptyList()
        )
    }
}