package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@Composable
fun DishSelectedItem(dish: Dish, dishSelectorViewModel: DishSelectorViewModel) {
    var description by rememberSaveable { mutableStateOf("") }


    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .height(100.dp)
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


            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 15.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ){
                    Text(
                        text = dish.name,
                        maxLines = 2,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = dish.formatedPrice(),
                        textAlign = TextAlign.Right
                    )
                }

                TextField(
                    shape = AbsoluteRoundedCornerShape(10.dp, 10.dp),
                    singleLine = true,
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(stringResource(R.string.description)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.text_field_bg)
                    )
                )
            }

            Image(
                painter = painterResource(id = R.drawable.bin_icon),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.End)
                    .width(30.dp)
                    .height(30.dp)
                    .padding(end = 5.dp)
                    .clickable {  }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDishSelectedItem(){
    DeliiciousWaitressTheme {
        DishSelectedItem(
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