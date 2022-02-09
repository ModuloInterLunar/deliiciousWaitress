package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.DishItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun DishSelectorScreen(
    navController: NavController,
    dishSelectorViewModel: DishSelectorViewModel
) {
    Scaffold (
        topBar = {
            TopBar(
                title = stringResource(id = R.string.dishes_list),
                buttonIcon = painterResource(id = R.drawable.back_arrow),
                onButtonClicked = { navController.popBackStack() }
            )
        },
        content = {
            DishSelectorContent(
                navController = navController,
                dishSelectorViewModel = dishSelectorViewModel
            )
        }
    )
}

@Composable
fun DishSelectorContent(navController: NavController, dishSelectorViewModel: DishSelectorViewModel){
    val dishes = dishSelectorViewModel.dishes.value
    val selectedDishes = dishSelectorViewModel.selectedDishes.value

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(text = "Lista de platos:")
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ){
                itemsIndexed(
                    items = dishes
                ){ _, dish ->
                    DishItem(
                        dish = dish,
                        dishSelectorViewModel = dishSelectorViewModel
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
            Text(text = "Platos Seleccionados:")
            LazyColumn(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ){
                itemsIndexed(
                    items = dishSelectorViewModel.selectedDishes.value
                ){
                    _, dish ->
                    DishSelectedItem(
                        dish = dish,
                        dishSelectorViewModel = dishSelectorViewModel
                    )
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    )
                }
            }
            
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = stringResource(id = R.string.send_dishes))
            }
        }
    }
}