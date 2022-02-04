package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.graphics.Color
import android.icu.number.Scale
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.base.R
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.model.IngredientModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme


@Composable
fun IngredientItem(navController: NavController, ingredient: Ingredient, id: Int) {
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
        Column(
            modifier = Modifier.padding(4.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = ingredient.quantity.toString() + ingredient.measure,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(4.dp),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview2() {
    DeliiciousWaitressTheme() {
        val ingredient = Ingredient(0,"","", "g", "sdsadsa", 2, 200)
        IngredientItem(navController = rememberNavController(), ingredient = ingredient, id = 0)
    }
}

