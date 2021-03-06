package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.exception.WrongCredentialsException
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Table
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.GetEmployeeFromTokenUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.LoginUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.UserPreferences
import com.orhanobut.logger.Logger
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val isLoading = mutableStateOf(false)
    private val loadError = mutableStateOf("")

    fun isLoading(): MutableState<Boolean> = isLoading
    fun loadError() : MutableState<String> = loadError

    val tables: MutableState<List<Table>> = mutableStateOf(listOf())
    var loginUseCase = LoginUseCase()
    val getEmployeeFromTokenUseCase = GetEmployeeFromTokenUseCase()


    fun login(username: String, password: String, context: Context, onSuccessCallback: () -> Unit){
        viewModelScope.launch {
            isLoading.value = true
            try {
                val token = loginUseCase(username, password)
                if (token.isNotEmpty()){
                    isLoading.value = false
                }
                Logger.i(token)
                val userPreferences = UserPreferences(context)
                userPreferences.saveAuthToken(token)
                onSuccessCallback()

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

    fun testToken(context: Context, onSuccessCallback: () -> Unit) {
        viewModelScope.launch {
            val authToken = UserPreferences(context).authToken
            authToken.collect { token ->
                try {
                    Logger.i("Token -> $token")
                    RetrofitHelper.setToken(token ?: "")
                    val response = getEmployeeFromTokenUseCase()
                    onSuccessCallback()
                }
                catch (e: Exception) {
                }
            }

        }
    }
}