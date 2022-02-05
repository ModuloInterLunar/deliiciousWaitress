package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel.IngredientViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : ComponentActivity() {

    private val ingredientViewModel: IngredientViewModel by viewModels()
    private val tableListViewModel: TableListViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.addLogAdapter(AndroidLogAdapter())

        setContent {
            var currentScreen by rememberSaveable{ mutableStateOf("login") }
            Logger.i(currentScreen)
            DeliiciousWaitressTheme {

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = currentScreen
                ) {
                    composable("login") {
                        LoginScreen(
                            navController = navController,
                            loginViewModel = loginViewModel
                        )
                    }
                    composable("main_screen") {
                        currentScreen = "main_screen"
                        ingredientViewModel.loadIngredients()
                        MainScreen(
                            navController = navController,
                            ingredientModelList = ingredientViewModel.ingredients.value
                        )
                    }
                    composable(
                        "table_screen/{tableId}",
                        arguments = listOf(
                            navArgument("tableId") {
                                type = NavType.StringType
                            }
                        )
                    ) {

                    }
                    composable(
                        route = "table_list_screen"
                    ){
                        tableListViewModel.loadTables()
                        TableListScreen(
                            navController = navController,
                            tableModelList = tableListViewModel.tables.value
                        )
                    }
                }
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
        val ingredients = ingredientViewModel.ingredients.value
        Column {
            val isLoading = ingredientViewModel.isLoading().value
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