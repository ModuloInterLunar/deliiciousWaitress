package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme


@Composable
fun IngredientItem(navController: NavController, ingredient: Ingredient, id: Int) {
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clickable {
            navController.navigate(route = "details/{id}")
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(4.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(2.dp)
            )
            Text(
                text = ingredient.quantity.toString() + ingredient.measure,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(4.dp),
                textAlign = TextAlign.End
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview2() {
    DeliiciousWaitressTheme() {
        val ingredient = Ingredient("0","g","Salsa", 2, 200.0)
        IngredientItem(navController = rememberNavController(), ingredient = ingredient, id = 0)
    }
}

