package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(title: String = "", buttonIcon: Painter, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "", Modifier.size(30.dp))
            }
        },
        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}