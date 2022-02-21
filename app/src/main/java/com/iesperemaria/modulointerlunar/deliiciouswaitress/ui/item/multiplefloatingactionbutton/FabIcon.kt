package com.iesperemaria.modulointerlunar.deliiciouswaitress.ui.item.multiplefloatingactionbutton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Immutable
interface  FabIcon {
    @Stable val imageVectorExpanded: ImageVector
    @Stable val imageVectorCollapsed: ImageVector
    @Stable val iconRotate: Float?
}

private class FabIconImpl(
    override val imageVectorExpanded: ImageVector,
    override val imageVectorCollapsed: ImageVector,
    override val iconRotate: Float?
) : FabIcon

fun FabIcon(
    imageVectorExpanded: ImageVector = Icons.Filled.ExpandLess,
    imageVectorCollapsed: ImageVector = Icons.Filled.ExpandMore,
    iconRotate: Float? = null
) : FabIcon =
    FabIconImpl(imageVectorExpanded, imageVectorCollapsed, iconRotate)