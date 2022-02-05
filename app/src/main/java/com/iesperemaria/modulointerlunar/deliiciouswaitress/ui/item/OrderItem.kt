package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order

@Composable
fun OrderItem(order: Order){
    Row(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(1.dp, Color.Black),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // IMAGE
        Text(
            text = order.dish.name,
            maxLines = 2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 5.dp)
        )
        
        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Text(
            text = order.description,
            maxLines = 3,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .widthIn(max = 200.dp)
        )

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.bin_icon),
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(30.dp)
                .height(30.dp)
                .padding(end = 5.dp)
                .clickable { /*TODO*/ }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewOrderItem(){
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
        hasBeenCoocked = false,
        hasBeenServed = false,
        isIncluded = false,
        table = "1",
        ticket = "1",
        description = "Sin sal"
    ))
}