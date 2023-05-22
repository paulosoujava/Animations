package com.paulo.animations.others

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.paulo.animations.dynamicIsland.App

@Composable
fun ShowAnim() {
    LazyColumn{
        item {
            Box(modifier = Modifier.height(40.dp)) {
                ExpandableFAB()

            }

        }
        item {
            ProgressLoader()
        }
        item {
            SwipeDismiss()
        }
        item {
            TextChange()
        }
        item {
            App()
        }
        item {
            Switch()
        }
    }
}