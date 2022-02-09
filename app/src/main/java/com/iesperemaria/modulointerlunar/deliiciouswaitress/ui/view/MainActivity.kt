package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.MainScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.dishselector.DishSelectorViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.table.TableViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.tablelist.TableListViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.theme.DeliiciousWaitressTheme
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginScreen
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.login.LoginViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.outputtray.OutputTrayViewModel
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.viewmodel.IngredientListViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val ingredientViewModel: IngredientListViewModel by viewModels()
    private val tableListViewModel: TableListViewModel by viewModels()
    private val tableViewModel: TableViewModel by viewModels()
    private val outputTrayViewModel: OutputTrayViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val dishSelectorViewModel: DishSelectorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.addLogAdapter(AndroidLogAdapter())

        setContent {
            MainScreen(
                tableListViewModel,
                tableViewModel,
                outputTrayViewModel,
                loginViewModel,
                dishSelectorViewModel
            )
        }
    }
}