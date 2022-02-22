package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.view

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun FAB(action : ()-> Unit) {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        action()
    }) {
        Text("+")
    }
}
