package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredientlist.IngredientListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.TopBar
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.toSize
import androidx.navigation.compose.rememberNavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.FAB


@Composable

fun IngredientScreen(
    navController: NavController,
    ingredientViewModel: IngredientViewModel
){
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(id = R.string.add_ingredient),
                buttonIcon = painterResource(id = R.drawable.hamburger_icon),
                onButtonClicked = { navController.popBackStack() },
                navController = navController
            )
        },
        content = {
            IngredientContent(
                navController = navController,
                ingredientViewModel = ingredientViewModel
            )
        }
    )
}


@Composable
fun IngredientContent(navController: NavController, ingredientViewModel: IngredientViewModel) {

    var ingredientName by rememberSaveable { mutableStateOf("") }
    var ingredientQuantity by rememberSaveable { mutableStateOf("") }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val measuresList = listOf("g", "ml", "uds.", "l", "kg")
    var selectedMeasure by rememberSaveable { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

    Surface() {
        Column() {
            Row() {
                OutlinedTextField(
                    value = ingredientName,
                    onValueChange = {
                        ingredientName = it
                    },
                    label = { Text("Nombre") }
                )
                OutlinedTextField(
                    value = ingredientQuantity,
                    onValueChange = {
                        ingredientQuantity = it
                    },
                    label = { Text("Cantidad") })
            }
            Row() {
                OutlinedTextField(value = selectedMeasure,
                    onValueChange = { selectedMeasure = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Medida seleccionada") },
                    trailingIcon = {
                        Icon(icon, "", Modifier.clickable { expanded = !expanded })
                    })

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
                ) {
                    measuresList.forEach { label ->
                        DropdownMenuItem(onClick = {
                            selectedMeasure = label
                            expanded = false
                        }) {
                            Text(text = label)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                ingredientViewModel.createIngredient()
            },
            modifier = Modifier
                .fillMaxWidth()){
                Text(text = "Enviar")
            }
        }
    }

}

@Preview
@Composable
fun previewIngredientScreen() {
    IngredientContent(navController = rememberNavController() , ingredientViewModel = IngredientViewModel())
}

