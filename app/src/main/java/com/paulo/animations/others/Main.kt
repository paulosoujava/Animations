package com.paulo.animations.others

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.paulo.animations.dynamicIsland.App

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ShowAnim() {
    Scaffold(
        bottomBar = {
           // ExpandableFAB()
        }
    ) {
        LazyColumn{
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

}