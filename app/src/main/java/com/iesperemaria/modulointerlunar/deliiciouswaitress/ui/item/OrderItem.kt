package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

@Composable
fun OrderItem(order: Order, imageId: Int, action: () -> Unit) {
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
                    data = order.dish.image,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = order.dish.name,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(55.dp)
                    .padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))


            Text(
                text = order.dish.name,
                maxLines = 2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 5.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .verticalScroll(rememberScrollState())
                    .widthIn(max = 180.dp)
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = order.description,
                    textAlign = TextAlign.Center,
                )
            }

            Spacer(modifier = Modifier.width(1.dp))

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "",
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
                    .wrapContentWidth(Alignment.End)
                    .width(30.dp)
                    .height(30.dp)
                    .padding(end = 5.dp)
                    .clickable { action() }
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewOrderItem() {
    OrderItem(
        order = Order(
            id = "1",
            employee = Employee(
                id = "1",
                dni = "abc",
                name = "Alvaro",
                surname = "Martinez"
            ),
            dish = Dish(
                id = "1",
                ingredientQties = emptyList(),
                name = "Huevo con patatas",
                price = 7.50,
                type = "Food"
            ),
            hasBeenCooked = false,
            hasBeenServed = false,
            isIncluded = false,
            table = "1",
            ticket = "1",
            description = "Sin sal"
        ),
        R.drawable.bin_icon,
    ) {

    }
}