package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.ticket

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Ticket
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.ticketusecase.GetTicketsPaidUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class TicketListViewModel: ViewModel() {
    private val TAG = "TicketViewModel"
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val timer = object : CountDownTimer(Long.MAX_VALUE, 1000) {
        override fun onTick(millisRemaning: Long) {
            loadTickets()
            Log.i(TAG,"ticking")
        }

        override fun onFinish() {
        }
    }

    val getTicketsPaidUseCase = GetTicketsPaidUseCase()
    val tickets: MutableState<List<Ticket>> = mutableStateOf(listOf())

    fun loadTickets(){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val result = getTicketsPaidUseCase()
                tickets.value = result
                isLoading.value = false
            } catch (e: Exception) {
                Logger.e(e.message ?: e.toString())
            }
        }
    }
}