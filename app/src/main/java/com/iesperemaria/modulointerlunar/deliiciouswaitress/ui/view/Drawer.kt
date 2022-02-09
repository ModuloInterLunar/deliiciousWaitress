package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.iesperemaria.modulointerlunar.deliiciouswaitress.R


@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinationClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 35.dp, top = 24.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Hamburger menu",
            modifier = Modifier.width(250.dp)
        )
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(id = R.string.tables),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    onDestinationClicked("table_list_screen")
                }
        )
        Text(
            text = stringResource(id = R.string.output_tray),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    onDestinationClicked("output_tray")
                }
        )
        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.h6,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp)
                .clickable {
                    onDestinationClicked("ingredient_screen")
                }
        )
    }
}
