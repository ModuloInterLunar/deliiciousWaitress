package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.DishItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DishSelectorScreen(
    navController: NavController,
    dishSelectorViewModel: DishSelectorViewModel
) {
    val config = LocalConfiguration.current
    val scaffoldState = rememberBackdropScaffoldState(
        BackdropValue.Revealed
    )
    var peekHeight = 50.dp
    var headerHeight = 30.dp
    if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
        peekHeight = 150.dp
        headerHeight = 150.dp
    }
    BackdropScaffold (
        scaffoldState = scaffoldState,
        appBar = {
            TopBar(
                title = stringResource(id = R.string.dishes_list),
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                onButtonClicked = { navController.popBackStack() }
            )
        },
        backLayerContent = {
            BackLayerDishSelectorContent(
                navController = navController,
                dishSelectorViewModel = dishSelectorViewModel
            )
        },
        frontLayerContent = {
            FrontLayerDishSelectorContent(
                navController = navController,
                dishSelectorViewModel = dishSelectorViewModel)
        },
        peekHeight = peekHeight,
        headerHeight = headerHeight,
        gesturesEnabled = true
    )
}

@Composable
fun BackLayerDishSelectorContent(navController: NavController, dishSelectorViewModel: DishSelectorViewModel){
    val dishes by rememberUpdatedState(newValue = dishSelectorViewModel.dishes.value)

    Surface {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            itemsIndexed(
                items = dishes
            ) { _, dish ->
                DishItem(
                    dish = dish
                ) {
                    dishSelectorViewModel.addOrder(dish)
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FrontLayerDishSelectorContent(navController: NavController, dishSelectorViewModel: DishSelectorViewModel) {
    val selectedOrders = dishSelectorViewModel.selectedOrders
     Surface {
         Column(
             modifier = Modifier
                 .fillMaxWidth()
                 .wrapContentSize(Alignment.Center)
         ) {
             Spacer(modifier = Modifier.padding(5.dp))
             Box(
                 modifier = Modifier
                     .width(60.dp)
                     .height(4.dp)
                     .clip(RoundedCornerShape(10.dp))
                     .background(Color.LightGray)
             )
             Spacer(modifier = Modifier.padding(5.dp))
         }
         LazyColumn(
             modifier = Modifier
                 .padding(10.dp)
                 .fillMaxWidth()
         ) {
            items(
                selectedOrders, { it._id }
            ) { order ->
                val dismissState = rememberDismissState()
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
                            .padding(horizontal = 12.dp))
                        {
                            Icon(
                                icon, 
                                contentDescription = "Icon", 
                                modifier = Modifier
                                    .scale(scale)
                            )
                        }
                    },
                    dismissContent = {
                        DishSelectorItem(
                            order = order,
                            elevation = animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 4.dp else 0.dp).value
                        )
                    }
                )


                Spacer(
                    modifier = Modifier
                        .height(5.dp)
                        .fillMaxWidth()
                )

            }
            item {
                Spacer(modifier = Modifier.padding(5.dp))
                if (selectedOrders.isNotEmpty())
                    Box(modifier = Modifier.fillMaxWidth()){
                        Button(
                            onClick = { /*TODO*/ },
                            Modifier.align(Alignment.Center)
                        ) {
                            Text(text = stringResource(id = R.string.send_dishes))
                        }
                    }
                Spacer(modifier = Modifier.padding(5.dp))
            }
        }
    }
}
