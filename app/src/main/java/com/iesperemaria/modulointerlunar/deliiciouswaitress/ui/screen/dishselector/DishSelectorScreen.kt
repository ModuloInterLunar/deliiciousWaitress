package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                    addDishToSelecteds(dish, dishSelectorViewModel)
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }
}

@Composable
fun FrontLayerDishSelectorContent(navController: NavController, dishSelectorViewModel: DishSelectorViewModel) {
    val selectedOrders = dishSelectorViewModel.selectedOrders
     Surface {
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            item{
                Text(
                    text = "Platos Seleccionados:",
                    color = Color.DarkGray,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(10.dp)
                )
            }
            itemsIndexed(
                items = selectedOrders
            ) { _, order ->
                DishSelectorItem(
                    order = order,
                    dishSelectorViewModel = dishSelectorViewModel
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

fun addDishToSelecteds(dish: Dish, dishSelectorViewModel: DishSelectorViewModel) {
    dishSelectorViewModel.selectedOrders =
        dishSelectorViewModel.selectedOrders + mutableListOf(
            Order(
                dish = dish,
                table = dishSelectorViewModel.table.value.id
            )
    )
}