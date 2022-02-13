package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.WrongCredentialsException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.LoginUseCase
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val tables: MutableState<List<Table>> = mutableStateOf(listOf())
    var loginUseCase = LoginUseCase()


    fun login(username: String, password: String, context: Context, ifSuccessfulCallback: () -> Unit){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val token = loginUseCase(username, password)
                if (token.isNotEmpty()){
                    isLoading.value = false
                }
                Logger.i(token)

                ifSuccessfulCallback()

            } catch (e: WrongCredentialsException) {
                Toast.makeText(
                    context,
                    context.getString(R.string.wrong_credentials_exception_message),
                    Toast.LENGTH_SHORT
                ).show()
                isLoading.value = false

            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    context.getString(R.string.connection_error_exception_message),
                    Toast.LENGTH_SHORT
                ).show()
                Logger.e(e.message ?: e.toString())
                isLoading.value = false

            }
        }
    }
}