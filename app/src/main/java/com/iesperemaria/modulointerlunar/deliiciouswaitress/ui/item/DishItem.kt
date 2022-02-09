package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@Composable
fun DishItem(dish: Dish, dishSelectorViewModel: DishSelectorViewModel) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = dish.image,
                    builder = { transformations(CircleCropTransformation()) }
                ),
                contentDescription = dish.name,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(55.dp)
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))


            Text(
                text = dish.name,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 5.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = dish.formatedPrice(),
                textAlign = TextAlign.Right,
            )

            Image(
                painter = painterResource(id = R.drawable.add_icon),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.End)
                    .width(30.dp)
                    .height(30.dp)
                    .padding(end = 5.dp)
                    .clickable { addDishToSelecteds(dish, dishSelectorViewModel) }
            )
        }
    }
}

fun addDishToSelecteds(dish: Dish, dishSelectorViewModel: DishSelectorViewModel) {
    val TAG = "AddDishToSelecteds"
    dishSelectorViewModel.selectedOrders.value.add(Order(
        dish = dish,
        table = dishSelectorViewModel.table.value.id
    ))
    Log.i(TAG, "${dishSelectorViewModel.selectedOrders.value.size}")
}

@Preview(showBackground = true)
@Composable
fun PreviewDishItem(){
    DeliiciousWaitressTheme {
        DishItem(
            dish = Dish(
                id = "1",
                description = "abc",
                image = "https://www.encopadebalon.com/3497-large_default/fanta-naranja-pack-24-unidades-33cl.jpg",
                ingredientQties = emptyList(),
                name = "Fanta de naranja",
                price = 2.00,
                type = "Food"
            ),
            dishSelectorViewModel =  DishSelectorViewModel()
        )
    }
}