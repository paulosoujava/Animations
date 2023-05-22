package com.paulo.animations.anims

import android.content.Context
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.animations.R

@Composable
fun ShowBox( context: Context) {
    motionExample1(context)
}
@OptIn(ExperimentalMotionApi::class)
@Composable
private fun motionExample1(ctx: Context) {
    val motionScene = remember {
        ctx.resources
            .openRawResource(R.raw.scene3)
            .readBytes()
            .decodeToString()
    }
    var animateEnd by remember{ mutableStateOf(false) }
    val progress by animateFloatAsState(targetValue = if (animateEnd) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        motionScene = MotionScene(content= motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
        Box(modifier = Modifier
            .layoutId("box_demo")
            .background(Color.Red)
            .clickable {
                animateEnd = !animateEnd
            },
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Box",
                modifier = Modifier
                    .layoutId("box_title_demo")
            )
        }
    }
}