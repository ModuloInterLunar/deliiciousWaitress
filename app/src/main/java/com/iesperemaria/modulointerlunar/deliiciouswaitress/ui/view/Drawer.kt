package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R
import com.iesperemaria.modulointerlunar.deliiciouswaitress.data.remote.responses.Employee
import com.iesperemaria.modulointerlunar.deliiciouswaitress.domain.employeeusecase.GetEmployeeFromTokenUseCase
import com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.screen.AppScreens

suspend fun getCurrentEmployee(): Employee {
    val getEmployeeFromTokenUseCase = GetEmployeeFromTokenUseCase()
    return getEmployeeFromTokenUseCase()
}

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit,
    currentEmployee: State<Employee> = mutableStateOf(Employee())
) {
    val screens = mutableListOf(
        AppScreens.TableListScreen,
        AppScreens.OutputTrayScreen,
        AppScreens.TicketListScreen
    )
    if (currentEmployee.value.isAdmin)
        screens.add(AppScreens.IngredientListScreen)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 35.dp, top = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Hamburger menu",
            modifier = Modifier.width(250.dp)
        )
        Spacer(Modifier.height(24.dp))
        screens.forEach { screen ->
            Text(
                text = stringResource(id = screen.name),
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .clickable {
                        onDestinationClicked(screen.route)
                    }
            )
        }
        Text(
            text = "Empleado: " + currentEmployee.value.fullName(),
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(Alignment.Bottom)
                .padding(bottom = 30.dp)
        )

    }
}
