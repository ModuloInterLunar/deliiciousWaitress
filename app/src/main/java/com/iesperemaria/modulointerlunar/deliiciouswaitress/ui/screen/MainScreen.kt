package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.IngredientItem
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel.IngredientViewModel
import retrofit2.Response

@Composable
fun MainScreen(
    navController: NavController,
    ingredientModelList: List<Ingredient>
) {
    @Composable
    fun IngredientList() {
        Column {
//            val isLoading = ingredientModelList.isLoading().value
//            if (isLoading)
//                CircularProgressIndicator()
            LazyColumn {
                itemsIndexed(items = ingredientModelList) {
                        index, item -> IngredientItem(id = index, navController = navController, ingredient = item)
                }
            }
        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "MainScreen",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            IngredientList()
        }
    }

}
