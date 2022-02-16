package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient.IngredientScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ingredient.IngredientViewModel
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket.TicketListScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket.TicketListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view.Drawer
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.createNotificationChannel
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    tableListViewModel: TableListViewModel,
    tableViewModel: TableViewModel,
    outputTrayViewModel: OutputTrayViewModel,
    loginViewModel: LoginViewModel,
    dishSelectorViewModel: DishSelectorViewModel,
    paymentViewModel: PaymentViewModel,
    ticketListViewModel: TicketListViewModel,
    ingredientViewModel: IngredientViewModel
) {
    var currentScreen by rememberSaveable { mutableStateOf(AppScreens.LoginScreen.route) }
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var gesturesEnabled by remember { mutableStateOf(false) }
    val openDrawer = {
        scope.launch {
            drawerState.open()
        }
    }
    val context = LocalContext.current
    createNotificationChannel(
        context.getString(R.string.app_name),
        context = context
    )
    Logger.i(currentScreen)

    DeliiciousWaitressTheme {
        val navController = rememberNavController()

        ModalDrawer(
            drawerState = drawerState,
            gesturesEnabled = gesturesEnabled,
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
                composable(AppScreens.LoginScreen.route) {
                    gesturesEnabled = false
                    // auto-login
                    loginViewModel.login("alvaro", "12345", context) {
                        outputTrayViewModel.loadOrders()
                        navController.navigate(AppScreens.TableListScreen.route){
                            popUpTo(0)
                        }
                    }
                    // auto-login
                    LoginScreen(
                        navController = navController,
                        loginViewModel = loginViewModel
                    )
                }
                composable(AppScreens.TableListScreen.route)
                {
                    gesturesEnabled = true
                    currentScreen = AppScreens.TableListScreen.route
                    tableListViewModel.timer.cancel()
                    tableListViewModel.timer.start()
                    TableListScreen(
                        navController = navController,
                        tableListViewModel = tableListViewModel
                    ) {
                        openDrawer()
                    }
                }
                composable(
                    AppScreens.TableScreen.route + "/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    gesturesEnabled = false
                    try {
                        tableViewModel.tableId = it.arguments?.getString("tableId")!!
                        tableViewModel.timer.cancel()
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
                composable(AppScreens.OutputTrayScreen.route) {
                    gesturesEnabled = true
                    outputTrayViewModel.timer.cancel()
                    outputTrayViewModel.timer.start()
                    OutputTrayScreen(
                        navController = navController,
                        outputTrayViewModel = outputTrayViewModel
                    ) {
                        openDrawer()
                    }
                }
                composable(AppScreens.IngredientScreen.route) {
                    IngredientScreen(
                        navController = navController,
                        ingredientViewModel = ingredientViewModel
                    )
                }
                composable(
                    AppScreens.DishSelectorScreen.route + "/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") { type = NavType.StringType }
                    )
                ) {
                    gesturesEnabled = false
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
                    AppScreens.PaymentScreen.route + "/{tableId}",
                    arguments = listOf(
                        navArgument("tableId") { type = NavType.StringType }
                    )
                ) {
                    gesturesEnabled = false
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
                composable(
                    AppScreens.TicketListScreen.route
                ) {
                    gesturesEnabled = true

                    TicketListScreen(
                        navController = navController,
                        ticketListViewModel = ticketListViewModel,
                        openDrawer = { openDrawer() }
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