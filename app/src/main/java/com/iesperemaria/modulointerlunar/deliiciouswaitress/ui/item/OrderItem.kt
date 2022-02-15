package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.MarqueeText

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Composable
fun OrderItem(order: Order, dismissState: DismissState? = null, imageId: Int? = null, imageAction: (() -> Unit)? = null) {
    var color by remember { mutableStateOf(R.color.dimmed_red)}
    if (order.hasBeenCooked) color = R.color.dimmed_orange
    if (order.hasBeenServed) color = R.color.dimmed_green
    Card(
        shape = RoundedCornerShape(8.dp),
        backgroundColor = colorResource(color),
        elevation = if (dismissState != null) animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 12.dp else 8.dp).value else 8.dp
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

            Box(
                Modifier
                    .fillMaxWidth(0.4f)
                    .align(Alignment.CenterVertically)
            ) {
                MarqueeText(
                    text = order.dish.name,
                    gradientEdgeColor = colorResource(id = color),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(0.7f)
                    .padding(vertical = 10.dp)
            ) {
                var description = ""
                if (dismissState == null) description = "Mesa ${order.table} "
                description += order.description
                MarqueeText(
                    text = description,
                    gradientEdgeColor = colorResource(id = color),
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
            }

            Spacer(modifier = Modifier.width(1.dp))

            if (imageId != null)
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
                        .clickable { imageAction!!() }
                )


        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
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
                name = "Empanadillas de atún",
                price = 7.50,
                type = "Food"
            ),
            hasBeenCooked = false,
            hasBeenServed = false,
            isIncluded = false,
            table = "1",
            ticket = "1",
            description = "Con extra de empanadilla"
        ),
        rememberDismissState(),
        imageId = R.drawable.checkmark
    )
}