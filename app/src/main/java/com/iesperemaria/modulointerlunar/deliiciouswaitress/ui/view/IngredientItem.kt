package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme


@Composable
fun IngredientItem(navController: NavController, ingredientModel: IngredientModel, id: Int) {
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(110.dp)
        .clickable {
            navController.navigate(route = "details/{id}")
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = ingredientModel.image,
                    builder = {
                        scale(coil.size.Scale.FILL)
                        placeholder(R.drawable.notification_action_background)
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = ingredientModel.name,
                modifier = Modifier.fillMaxHeight()
                    .weight(0.2f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview2() {
    DeliiciousWaitressTheme() {
        val ingredient = IngredientModel("aaaa", "sdsadsa", 2.0, "g", "asdf")
        IngredientItem(navController = rememberNavController(), ingredientModel = ingredient, id = 0)
    }
}

