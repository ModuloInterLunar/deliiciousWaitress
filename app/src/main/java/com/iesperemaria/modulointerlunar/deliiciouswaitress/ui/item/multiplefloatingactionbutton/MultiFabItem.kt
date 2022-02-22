package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

class MultiFabItem(
    val id: Int,
    val imageVector: ImageVector,
    val label: String = "",
    val color: Color? = null,
    val onClick: () -> Unit
)
