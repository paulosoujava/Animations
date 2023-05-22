package com.paulo.animations.anims

import android.app.SharedElementCallback
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Button
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// OPTION  + ENTER to import
@Composable
fun ShowOtherAnimations() {
    var editable by remember { mutableStateOf(true) }
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current

    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {

        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
                ){
            Button(onClick = {  editable = !editable}) {
                Text(text = "SHOW EXAMPLE 1")
            }
            Button(onClick = {  visible = !visible}) {
                Text(text = "SHOW EXAMPLE 2")
            }

        }
        AnimateEntryAchild()

        Example0()

        Example1(editable)


        Example2(visible, density)
    }

}

@Composable
fun Example0() {

    var state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = {state.targetState = !state.targetState  }) {
            Text(text = "STATUS ANIMATION")

        }
    }
    Column {
        AnimatedVisibility(visibleState = state) {
            Text(text = "STATUS ANIMATION", fontSize = 24.sp)
        }

        // Use the MutableTransitionState to know the current animation state
        // of the AnimatedVisibility.
        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            },
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateEntryAchild() {
    var visible by remember { mutableStateOf(true) }
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = {visible = !visible  }) {
            Text(text = "STATUS ANIMATION")

        }
    }

    Column {

        Row {
            var count by remember { mutableStateOf(0) }
            Button(onClick = { count++ }) {
                Text("Add")
            }
            AnimatedContent(targetState = count) { targetCount ->
                // Make sure to use `targetCount`, not `count`.
                Text(text = "Count: $targetCount")
            }
        }

        val alpha: Float by animateFloatAsState(if (visible) 1f else 0.5f)
        Box(
            Modifier.size(230.dp, 30.dp)
                .graphicsLayer (alpha = alpha)
                .background(Color.Red)

        )

        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) { // this: AnimatedVisibilityScope
            // Use AnimatedVisibilityScope#transition to add a custom animation
            // to the AnimatedVisibility.
            val background by transition.animateColor(label = "") { state ->
                if (state == EnterExitState.PostExit) Color.Green else Color.DarkGray
            }
            Box(modifier = Modifier
                .size(128.dp)
                .background(background))
        }
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            // Fade in/out the background and the foreground.
            Box(
                Modifier
                    .size(80.dp)
                    .background(Color.DarkGray)) {
                Box(
                    Modifier
                        .align(Alignment.Center)
                        .animateEnterExit(
                            // Slide in/out the inner box.
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        )
                        .sizeIn(minWidth = 56.dp, minHeight = 64.dp)
                        .background(Color.Red)
                ) {
                    // Content of the notification…
                }
            }
        }
    }

}

@Composable
private fun ColumnScope.Example1(editable: Boolean) {
    AnimatedVisibility(visible = editable) {
        Text(
            text = "Edit",
            fontSize = 34.sp,
            modifier = Modifier
                .size(100.dp)
                .background(Color.Green)
        )
    }
}

@Composable
private fun ColumnScope.Example2(visible: Boolean, density: Density) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { -40.dp.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        Text(
            "Hello",
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red),
            fontSize = 34.sp
        )
    }
}

