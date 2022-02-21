package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.FAB
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar

@Composable
fun IngredientScreen(
    navController: NavController,
    ingredientViewModel: IngredientViewModel,
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
            IngredientContent(
                navController = navController,
                ingredientViewModel = ingredientViewModel
            )
        },
        floatingActionButton = {
            FAB {

            }
        }
    )
}

@Composable
fun IngredientContent(navController: NavController, ingredientViewModel: IngredientViewModel) {

    val ingredients = ingredientViewModel.ingredients.value

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
                            ingredientViewModel.updateIngredient(ingredient = ingredient)
                        }
                }
            }
        }
    }
}
