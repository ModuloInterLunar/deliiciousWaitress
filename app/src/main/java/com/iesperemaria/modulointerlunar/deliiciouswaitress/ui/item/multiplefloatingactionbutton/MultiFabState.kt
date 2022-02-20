package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

sealed class MultiFabState {
    object  Collapsed: MultiFabState()
    object  Expand: MultiFabState()

    fun isExpanded() = this == Expand
    fun toggleValue() = if(isExpanded()) {
        Collapsed
    }else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() = remember { mutableStateOf<MultiFabState>(MultiFabState.Collapsed) }