package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.widget.Toast
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
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
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
import kotlin.math.roundToInt

@Composable
fun TableListItem(navController: NavController, tableListViewModel: TableListViewModel, tableId: String, parentSize: IntSize) {
    val table = tableListViewModel.tables.value.find { it.id == tableId }!!
    val offsetX = remember { mutableStateOf(table.posX * parentSize.width) }
    val offsetY = remember { mutableStateOf(table.posY * parentSize.height) }

    Box(
        modifier = Modifier
            .offset {
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consumeAllChanges()

                    Toast
                        .makeText(navController.context, "$parentSize", Toast.LENGTH_SHORT)
                        .show()

                    val newOffset = correctOutOfParent(
                        newPosition = Offset(dragAmount.x, dragAmount.y),
                        radius = 25,
                        size = parentSize
                    )

                    offsetX.value += newOffset.x
                    offsetY.value += newOffset.y
                }
            }
            .size(50.dp)
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
fun correctOutOfParent(newPosition: Offset, radius: Int, size: IntSize): Offset {
    if (size.width == 0 || size.height == 0)
        return newPosition

    var x: Float = newPosition.x
    var y: Float = newPosition.y

    if (x + radius > size.width)
        x = (size.width - radius).toFloat()
    if (y + radius > size.height)
        y = (size.height - radius).toFloat()

    return Offset(x, y)
}