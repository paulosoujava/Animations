package com.paulo.animations.island2

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun ShowMain2() {
    LazyColumn{
        item { DynamicIsland() }
        item { DynamicIslandDemo2() }
        item { DynamicIslandDemo3() }
    }
}