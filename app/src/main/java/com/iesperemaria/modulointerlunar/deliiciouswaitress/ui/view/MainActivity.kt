package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.MainScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.payment.PaymentViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket.TicketListViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : ComponentActivity() {

    private val tableListViewModel: TableListViewModel by viewModels()
    private val tableViewModel: TableViewModel by viewModels()
    private val outputTrayViewModel: OutputTrayViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val dishSelectorViewModel: DishSelectorViewModel by viewModels()
    private val paymentViewModel: PaymentViewModel by viewModels()
    private val ticketListViewModel: TicketListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.addLogAdapter(AndroidLogAdapter())

        setContent {
            MainScreen(
                tableListViewModel,
                tableViewModel,
                outputTrayViewModel,
                loginViewModel,
                dishSelectorViewModel,
                paymentViewModel,
                ticketListViewModel,
                intent
            )
        }
    }
}