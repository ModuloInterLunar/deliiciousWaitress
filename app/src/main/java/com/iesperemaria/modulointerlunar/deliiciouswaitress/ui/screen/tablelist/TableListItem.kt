package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist

import android.os.CountDownTimer
import android.util.Log
import android.util.Size
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import kotlinx.coroutines.launch
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
                    if(!tableListViewModel.canMoveTables)
                        return@detectDragGestures
                    offsetX.value += dragAmount.x
                    offsetY.value += dragAmount.y
                }
            }
            .size(50.dp)
            .onGloballyPositioned {
                val newCoords = correctOutOfParent(
                    it.positionInParent(),
                    it.size,
                    it.parentLayoutCoordinates?.size ?: IntSize(0, 0),
                    table
                )

                offsetX.value = newCoords.x.toDouble()
                offsetY.value = newCoords.y.toDouble()
                table.posX = newCoords.x.toDouble() / it.parentLayoutCoordinates?.size?.width!!
                table.posY = newCoords.y.toDouble() / it.parentLayoutCoordinates?.size?.height!!
            }
            .clip(CircleShape)
            .background(colorResource(id = R.color.table_color))
            .clickable {
                navController.navigate("${AppScreens.TableScreen.route}/${table.id}")
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
fun correctOutOfParent(newPosition: Offset, size: IntSize, parentSize: IntSize, table: Table): Offset {
    var x = newPosition.x
    var y = newPosition.y

    if(newPosition.x == 0f && newPosition.y == 0f && table.posX != 0.0 && table.posY != 0.0){
        x = (table.posX * parentSize.width).toFloat()
        y = (table.posY * parentSize.height).toFloat()
        return Offset(x, y)
    }

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