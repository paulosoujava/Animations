package com.paulo.animations.others1

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ShowOthers() {
    LazyColumn{
        item { Others1() }
        item { Others2()}
        item {  Others5() }
        item {  Others3()}
        item {  Others4() }
    }
}

@Composable
fun Others1() {

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .size(200.dp)
            .background(color))
}

@Composable
fun Others5() {
    val enabled = remember {
        mutableStateOf(true)
    }
    val value by animateFloatAsState(
        targetValue = if (enabled.value) 100f else 305f,
        animationSpec = keyframes {
            durationMillis = 1000
            0.0f at 0 with LinearOutSlowInEasing // for 0-15 ms
            0.2f at 15 with FastOutLinearInEasing // for 15-75 ms
            0.4f at 75 // ms
            0.4f at 225 // ms
        }
    )
    Box(
        Modifier
            .size(value.dp, 40.dp)
            .background(Color.Blue)
            .clickable {
                enabled.value = !enabled.value
            }
    )
}
@Composable
fun Others3() {
    val enabled = remember {
        mutableStateOf(true)
    }
    val value by animateFloatAsState(
        targetValue =  if (enabled.value) 100f else 305f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        )
    )
    Box(
        Modifier
            .size(value.dp, 40.dp)
            .background(Color.Cyan)
            .clickable {
                enabled.value = !enabled.value
            }
    )
}
@Composable
fun Others4() {
    val enabled = remember {
        mutableStateOf(true)
    }
    val value by animateFloatAsState(
        targetValue = if (enabled.value) 100f else 305f,
        animationSpec = tween(
            durationMillis = 300,
            delayMillis = 50,
            easing = LinearOutSlowInEasing
        )
    )
    Box(
        Modifier
            .size(value.dp, 40.dp)
            .background(Color.Green)
            .clickable {
                enabled.value = !enabled.value
            }
    )
}

@Composable
fun Others2() {

    val enabled = remember {
        mutableStateOf(true)
    }
    val alpha: Float by animateFloatAsState(
        targetValue = if (enabled.value) 1f else 0.5f,
        // Configure the animation duration and easing.
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing)
    )
    Box(
        Modifier
            .size(200.dp)
            .background(Color.DarkGray.copy(alpha = alpha))
            .clickable {
                enabled.value = !enabled.value
            }
    )
}