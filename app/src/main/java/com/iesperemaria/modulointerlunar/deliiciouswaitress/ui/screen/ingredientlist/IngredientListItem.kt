package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredientlist

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ingredient
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IngredientItem(navController: NavController, ingredient: Ingredient, action: ()->Unit) {
    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(50.dp)
        .clickable { action() },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = ingredient.name,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(2.dp)
                    .align(Alignment.CenterVertically)
            )
            Text (
                text = ingredient.quantity.toString() + ingredient.measure,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(4.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .align(Alignment.CenterVertically),
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
        ExtendIngredientItem( ingredient = ingredient, action = {}){}
    }
}

@Composable
fun ExtendIngredientItem(ingredient: Ingredient, action: () -> Unit, send: ()-> Unit) {
    var quantityToAdd by rememberSaveable { mutableStateOf("") }
    var totalQuantity by rememberSaveable { mutableStateOf( ingredient.quantity) }
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(8.dp, 4.dp)
        .fillMaxWidth()
        .height(150.dp)
        .clickable {
            action()
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = ingredient.name,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)
                )
                Text (
                    text = ingredient.quantity.toString() + ingredient.measure,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.End
                )
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                TextField(
                    value = quantityToAdd,
                    onValueChange = {
                        quantityToAdd = it },
                    label = {Text ("Cantidad")},
                    modifier = Modifier.width(100.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(
                    onClick = {
                        if (ingredient.quantity - quantityToAdd.toDouble() >= 0 )
                            totalQuantity = ingredient.quantity - quantityToAdd.toDouble()
                        else
                            Toast.makeText(context,"La cantidad total tiene que ser positiva", Toast.LENGTH_SHORT)
                    }
                ) {
                    Text ("-")
                }

                Button(onClick = {
                    if (ingredient.quantity + quantityToAdd.toDouble() >= 0 )
                        totalQuantity = quantityToAdd.toDouble() + ingredient.quantity
                    else
                        Toast.makeText(context,"La cantidad total tiene que ser positiva", Toast.LENGTH_SHORT)
                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(
                            id = R.color.purple_200
                        )
                    )
                ) {
                    Text ("+")
                }
                Button(
                    colors = ButtonDefaults.buttonColors(
                        colorResource(
                        id = R.color.purple_500
                        )
                    ),
                    onClick = {
                        if (quantityToAdd.toDouble() >= 0 )
                            totalQuantity = quantityToAdd.toDouble()
                        else
                            Toast.makeText(context,"La cantidad total tiene que ser positiva", Toast.LENGTH_SHORT)
                    },
                ) {
                    Text ("Total")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                Text(
                    text = "Total: " + totalQuantity,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(2.dp)
                        .align(Alignment.CenterVertically)
                )
                Button(onClick = {
                    ingredient.quantity = totalQuantity
                   send()
                }) {
                    Text ("Enviar")
                }
            }
        }
    }
}