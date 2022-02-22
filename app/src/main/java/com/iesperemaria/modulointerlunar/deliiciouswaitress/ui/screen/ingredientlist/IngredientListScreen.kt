package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredientlist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun IngredientListScreen(
    navController: NavController,
    ingredientListViewModel: IngredientListViewModel,
    openDrawer: () -> Unit
){
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.ingredients),
                buttonIcon = painterResource(id = R.drawable.hamburger_icon),
                onButtonClicked = { openDrawer() },
                navController = navController
            )
        },
        content = {
            IngredientListContent(
                navController = navController,
                ingredientListViewModel = ingredientListViewModel
            )
        },
        floatingActionButton = {
            IngredientFAB(navController = navController, ingredientListViewModel = ingredientListViewModel)
        }
    )
}

@Composable
fun IngredientListContent(navController: NavController, ingredientListViewModel: IngredientListViewModel) {

    val ingredients = ingredientListViewModel.ingredients.value

    Surface {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn( ){
                items(ingredients){ ingredient ->
                    var expandedExtendedIngredient by rememberSaveable { mutableStateOf (false) }
                    if (!expandedExtendedIngredient)
                        IngredientItem(navController = navController, ingredient = ingredient){ expandedExtendedIngredient = !expandedExtendedIngredient}
                    else
                        ExtendIngredientItem(ingredient = ingredient, action = { expandedExtendedIngredient = !expandedExtendedIngredient}){
                            ingredientListViewModel.updateIngredient(ingredient = ingredient)
                        }
                }
                item{
                    Spacer(Modifier.height(80.dp))
                }
            }
        }
    }
}

@Composable
fun IngredientFAB(navController: NavController, ingredientListViewModel: IngredientListViewModel) {

    FloatingActionButton(onClick = {
        navController.navigate(
            AppScreens.IngredientScreen.route
        )
    }) {
        Text("+")
    }
}

