package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.util.Log
import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import java.util.logging.Logger
import kotlin.math.roundToInt


val TAG = "table_list_item"

@Composable
fun TableListItem(navController: NavController, tableListViewModel: TableListViewModel, tableId: String, parentSize: IntSize) {
    val table = tableListViewModel.tables.find { it.id == tableId }!!
    val offsetX = remember { mutableStateOf(table.posX * parentSize.width) }
    val offsetY = remember { mutableStateOf(table.posY * parentSize.height) }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.value.toInt(),
                    y = offsetY.value.toInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
            .size(50.dp)
            .onGloballyPositioned {
                val newCoords = correctOutOfParent(
                    it.positionInParent(),
                    it.size,
                    it.parentLayoutCoordinates?.size ?: IntSize(0, 0)
                )
                offsetX.value = newCoords.x.toDouble()
                offsetY.value = newCoords.y.toDouble()
            }
            .clip(CircleShape)
            .background(colorResource(id = R.color.table_color))
            .clickable {
                navController.navigate("table_screen/${table.id}")
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = table.id,
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    DeliiciousWaitressTheme {
        val table = Table(id = "1")
        TableListItem(rememberNavController(), TableListViewModel(), tableId = table.id, IntSize(100, 100))
    }
}


// Not working
fun correctOutOfParent(newPosition: Offset, size: IntSize, parentSize: IntSize): Offset {
    var x = newPosition.x
    var y = newPosition.y

    if (x + size.width > parentSize.width)
        x = (parentSize.width - size.width).toFloat()
    else if(x < 0)
        x = 0f

    if (y + size.height > parentSize.height)
        y = (parentSize.height - size.height).toFloat()
    else if(y < 0)
        y = 0f

    return Offset(x, y)
}