package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomSwipeToDismiss(
    dishSelectorViewModel: DishSelectorViewModel,
    order: Order,
    dismissState: DismissState,
    content: @Composable () -> Unit
) {
    if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
        dishSelectorViewModel.removeOrder(order)
    }
    SwipeToDismiss(
        state = dismissState,
        directions = setOf(DismissDirection.StartToEnd),
        dismissThresholds = { FractionalThreshold(0.3f) },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            val color by animateColorAsState(
                targetValue = when (dismissState.targetValue) {
                    DismissValue.Default -> Color.LightGray
                    DismissValue.DismissedToEnd -> Color.Red
                    DismissValue.DismissedToStart -> Color.Red
                }
            )

            val icon = when(direction) {
                DismissDirection.StartToEnd -> Icons.Default.Delete
                DismissDirection.EndToStart -> Icons.Default.Delete
            }

            val scale by animateFloatAsState(targetValue = if (dismissState.targetValue == DismissValue.Default) 0.8f else 1.2f)

            Box(modifier = Modifier
                .fillMaxSize()
                .background(color)
                .align(Alignment.CenterVertically)
            ) {
                Icon(
                    icon,
                    contentDescription = "Icon",
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterStart)
                        .padding(start = 5.dp)
                        .scale(scale)
                )
            }
        },
        dismissContent = { content() }
    )
}