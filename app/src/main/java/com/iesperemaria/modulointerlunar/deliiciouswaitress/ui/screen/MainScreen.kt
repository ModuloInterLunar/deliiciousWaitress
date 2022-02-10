package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.ItemNotFoundException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment.PaymentScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment.PaymentViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.Drawer
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch
import java.lang.Exception

@Composable
fun MainScreen(
    tableListViewModel: TableListViewModel,
    tableViewModel: TableViewModel,
    outputTrayViewModel: OutputTrayViewModel,
    loginViewModel: LoginViewModel,
    dishSelectorViewModel: DishSelectorViewModel,
    paymentViewModel: PaymentViewModel
) {
    var currentScreen by rememberSaveable { mutableStateOf("login") }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    val context = LocalContext.current
    Logger.i(currentScreen)

    DeliiciousWaitressTheme {
        val navController = rememberNavController()

        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = true,
            drawerContent = {
                Drawer(
                    onDestinationClicked = { route ->
                        scope.launch {
                            drawerState.close()
                        }
                        navController.navigate(route) {
                            popUpTo = navController.graph.startDestinationId
                            launchSingleTop = true
                        }
                    }
                )
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = currentScreen
            ) {
                composable("login") {
                    // auto-login
                    loginViewModel.login("alvaro", "12345", navController, context)
                    // auto-login
                    LoginScreen(
                        navController = navController,
                        loginViewModel = loginViewModel
                    )
                }
                composable("table_list_screen")
                {
                    currentScreen = "table_list_screen"
                    tableListViewModel.timer.start()
                    TableListScreen(
                        navController = navController,
                        tableListViewModel = tableListViewModel
                    ) {
                        openDrawer()
                    }
                }
                composable(
                    "table_screen/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    try {
                        tableViewModel.tableId = it.arguments?.getString("tableId")!!
                        tableViewModel.timer.start()
                    } catch (e: ItemNotFoundException) {
                        Toast.makeText(
                            context,
                            stringResource(id = R.string.table_not_found_exception_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    TableScreen(
                        navController = navController,
                        tableViewModel = tableViewModel
                    )
                }
                composable("output_tray") {
                    outputTrayViewModel.timer.start()
                    OutputTrayScreen(
                        navController = navController,
                        outputTrayViewModel = outputTrayViewModel
                    ) {
                        openDrawer()
                    }
                }
                composable("ingredient_screen") {
                    IngredientScreen(navController = navController)
                }
                composable(
                    "dish_selector_screen/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") { type = NavType.StringType }
                    )
                ) {
                    try{
                        dishSelectorViewModel.loadTable(it.arguments?.getString("tableId")!!)
                        dishSelectorViewModel.loadEmployee()
                        dishSelectorViewModel.loadDishes()
                    }catch (e: ItemNotFoundException){
                        Toast.makeText(
                            context,
                            stringResource(id = R.string.table_not_found_exception_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    DishSelectorScreen(
                        navController = navController,
                        dishSelectorViewModel = dishSelectorViewModel
                    )
                }
                composable(
                    "payment_screen/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") { type = NavType.StringType }
                    )
                ) {
                    try{
                        paymentViewModel.loadTable(it.arguments?.getString("tableId")!!)
                    }catch (e: ItemNotFoundException){
                        Toast.makeText(
                            context,
                            stringResource(id = R.string.table_not_found_exception_message),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    PaymentScreen(
                        navController = navController,
                        paymentViewModel = paymentViewModel
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
}


@Composable
fun Toolbar() {
    TopAppBar(title = { Text(text = "Hola mundo") })
}

@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context, "Suscríbete a MoureDev", Toast.LENGTH_SHORT).show()
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