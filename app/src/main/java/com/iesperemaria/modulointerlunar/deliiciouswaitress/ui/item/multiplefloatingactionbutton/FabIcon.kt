package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
interface  FabIcon {
    @Stable val imageVector: ImageVector
    @Stable val iconRotate: Float?
}

private class FabIconImpl(
    override val imageVector: ImageVector,
    override val iconRotate: Float?
) : FabIcon

fun FabIcon(
    imageVector: ImageVector,
    iconRotate: Float? = null
) : FabIcon =
    FabIconImpl(imageVector, iconRotate)