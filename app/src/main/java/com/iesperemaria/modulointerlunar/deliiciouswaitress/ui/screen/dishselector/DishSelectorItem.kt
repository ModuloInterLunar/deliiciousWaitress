package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Dish
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Order
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@OptIn(ExperimentalCoilApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun DishSelectorItem(order: Order, dismissState: DismissState) {
    var description by rememberSaveable { mutableStateOf("") }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = animateDpAsState(targetValue = if (dismissState.dismissDirection != null) 12.dp else 8.dp).value
    ) {
        Row(
            modifier = Modifier
                .height(120.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = rememberImagePainter(
                    data = order.dish.image,
                    builder = { transformations(CircleCropTransformation()) }
                ),
                contentDescription = order.dish.name,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(55.dp)
                    .padding(start = 8.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth()
                ){
                    Text(
                        text = order.dish.name,
                        maxLines = 2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.Start)
                    )

                    Text(
                        text = order.dish.formatedPrice(),
                        textAlign = TextAlign.Right,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth(Alignment.End)
                    )
                }
                TextField(
                    shape = AbsoluteRoundedCornerShape(10.dp, 10.dp),
                    singleLine = true,
                    value = description,
                    onValueChange = {
                        description = it
                        order.description = it
                    },
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(),
                    label = { Text(stringResource(R.string.description)) },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.text_field_bg)
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun DishSelectorItemPrev() {
    DishSelectorItem(Order(dish = Dish(name="coca cola")), dismissState = rememberDismissState())
}
