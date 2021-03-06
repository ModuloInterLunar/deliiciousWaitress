package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.DishItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.CustomSwipeToDismiss
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

    BackHandler(true) {
        dishSelectorViewModel.selectedOrders = emptyList()
        navController.popBackStack()
    }

    BackdropScaffold (
        scaffoldState = scaffoldState,
        appBar = {
            TopBar(
                title = stringResource(id = AppScreens.DishSelectorScreen.name),
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                navController = navController,
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
    Surface {
        Column {
            SearchBar(Modifier.fillMaxWidth(), hint = "Buscar un plato") {
                dishSelectorViewModel.shownDishes = dishSelectorViewModel.dishes.filter { dish -> dish.name.lowercase().contains(it) }
            }
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                items(
                    dishSelectorViewModel.shownDishes,
                    { it.id }
                ) { dish ->
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
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FrontLayerDishSelectorContent(navController: NavController, dishSelectorViewModel: DishSelectorViewModel) {
    val context = LocalContext.current
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
             item{
                 Text(
                     text = stringResource(id = R.string.selected_dishes),
                     color = Color.DarkGray,
                     fontWeight = FontWeight.SemiBold,
                     modifier = Modifier.padding(10.dp)
                 )
             }
            items(
                selectedOrders, { it._id }
            ) { order ->
                val dismissState = rememberDismissState()
                CustomSwipeToDismiss(
                    swipeAction = {
                        dishSelectorViewModel.removeOrder(order)
                    },
                    dismissState
                ) {
                    DishSelectorItem(
                        order = order,
                        dismissState = dismissState
                    )
                }
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
                            onClick = {
                                dishSelectorViewModel.sendOrders(context) {
                                    navController.popBackStack()
                                }
                            },
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


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }

    Box(modifier = modifier) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            label = {
                Text(text = hint)
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = colorResource(id = R.color.white)
            ),
            onValueChange = {
                text = it
                onSearch(it.lowercase())
            }
        )
    }
}