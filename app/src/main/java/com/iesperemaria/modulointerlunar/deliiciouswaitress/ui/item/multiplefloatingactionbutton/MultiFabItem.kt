package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton

import androidx.annotation.DrawableRes

class MultiFabItem(
    val id: Int,
    @DrawableRes val iconRes: Int,
    val label: String = "",
    val onClick: () -> Unit
)
