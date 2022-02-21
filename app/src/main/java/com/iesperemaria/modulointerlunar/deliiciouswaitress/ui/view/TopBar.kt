package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.core.RetrofitHelper
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens
import com.iesperemaria.modulointerlunar.deliiciouswaitress.util.UserPreferences
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun TopBar(title: String = "", buttonIcon: Painter, navController: NavController, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "menu icon", Modifier.size(30.dp), tint = colorResource(
                    id = R.color.lighter_grey
                ))
            }
        },
        actions = {
            val context = LocalContext.current
            IconButton(onClick = { disconnect(context, navController) }) {
                Icon(Icons.Filled.Logout, contentDescription = "Logout", tint = colorResource(id = R.color.lighter_grey))
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}

fun disconnect(context: Context, navController: NavController) {
    runBlocking{
        launch {
            UserPreferences(context).saveAuthToken("")
            RetrofitHelper.setToken("")
            navController.navigate(AppScreens.LoginScreen.route) {
                popUpTo(0)
            }
        }
    }
}