package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R

sealed class AppScreens(val route: String, val name: Int) {
    object DishSelectorScreen: AppScreens("dish_selector_screen", R.string.dish_selector)
    object LoginScreen: AppScreens("login_screen", R.string.login)
    object OutputTrayScreen: AppScreens("output_tray_screen", R.string.output_tray)
    object PaymentScreen: AppScreens("payment_screen", R.string.payment)
    object TableScreen: AppScreens("table_screen", R.string.table)
    object TableListScreen: AppScreens("table_list_screen", R.string.tables)
    object IngredientListScreen: AppScreens("ingredient_list_screen", R.string.ingredients)
    object TicketListScreen: AppScreens("ticket_list_screen", R.string.paid_tickets)
    object IngredientScreen: AppScreens("ingredient_screen", R.string.add_ingredient)
}
