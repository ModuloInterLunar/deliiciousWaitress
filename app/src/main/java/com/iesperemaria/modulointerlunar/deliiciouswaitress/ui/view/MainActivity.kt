package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.MainScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel.IngredientViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : ComponentActivity() {

    private val viewModel: IngredientViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.addLogAdapter(AndroidLogAdapter())
        viewModel.loadIngredients()

        setContent {
            DeliiciousWaitressTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        MainScreen(
                            navController = navController,
                            ingredientModelList = viewModel.ingredients.value
                        )
                    }
                    composable(
                        "table_screen",
                        arguments = listOf(
                            navArgument("tableId") {
                                type = NavType.StringType
                            }
                        )
                    ) {

                    }
                }
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    ViewContainer()
//                }
            }
        }
    }
    @Preview
    @Composable
    fun ViewContainer() {
        Scaffold(
            topBar = { Toolbar() },
            content = { Test() },
            floatingActionButton = { FAB() },
            floatingActionButtonPosition = FabPosition.End
        )
    }

    @Composable
    fun Test() {
        val ingredients = viewModel.ingredients.value
        Column {
            val isLoading = viewModel.isLoading().value
            if (isLoading)
                CircularProgressIndicator()
            for (ingredient in ingredients)
                Text(text = ingredient.name, color = Color.Black)
        }
    }
}



@Composable
fun Toolbar() {
    TopAppBar(title = { Text(text = "Hola mundo") })
}

@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context, "Suscríbete", Toast.LENGTH_SHORT).show()
    }) {
        Text("+")
    }
}


@Composable
fun Greeting() {
    Text(text = "Hello !", Modifier.padding(5.dp))
}



@Composable
fun DefaultPreview() {
    DeliiciousWaitressTheme {
    }
}