package com.paulo.animations.dynamicIsland.island

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.twotone.Face

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FaceUnlock() {
    Icon(
        imageVector = Icons.TwoTone.Face,
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        tint = Color.Blue
    )
}