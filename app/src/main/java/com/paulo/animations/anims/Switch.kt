package com.paulo.animations.anims

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout


@Composable
fun ContentView(modifier: Modifier = Modifier) {
    val animateState = remember {
        mutableStateOf(false)
    }
    val surfaceBackground by animateColorAsState(
        targetValue = if (animateState.value) {
            Color.White
        } else {
            Color.Black
        }, animationSpec = tween(600)
    )
    Surface(color = surfaceBackground, modifier = modifier.fillMaxSize()) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.padding(vertical = 30.dp))
                Swipe(width = 320, height = 60, animatedState = animateState)
            }
        }

    }

}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun Swipe(modifier: Modifier = Modifier, width: Int, height: Int, animatedState: MutableState<Boolean>) {
    val progressState by animateFloatAsState(targetValue = if (animatedState.value) 1f else 0f)
    val switchBackground by animateColorAsState(
        targetValue = if (animatedState.value) {
            Color.LightGray
        } else {
            Color.DarkGray
        }, animationSpec = tween(600)
    )
    val switchColor by animateColorAsState(
        targetValue = if (animatedState.value) {
            Color(0xFF2E7D32)
        } else {
            Color(0xFFF44336)
        }, animationSpec = tween(600)
    )
    val textOffFontSize by animateIntAsState(
        targetValue = if (animatedState.value) 22 else 32, animationSpec = tween(600)
    )
    val textOnFontSize by animateIntAsState(
        targetValue = if (animatedState.value) 32 else 22, animationSpec = tween(600)
    )

    MotionLayout(
        start = startConstraints(parentHeight = height, parentWidth = width),
        end = endConstraints(parentHeight = height, parentWidth = width),
        progress = progressState
    ) {
        Box(
            modifier = modifier
                .layoutId("background")
                .clip(RoundedCornerShape(50))
                .background(switchBackground)

        )
        Box(
            modifier = modifier
                .layoutId("switch")
                .clip(RoundedCornerShape(50))
                .background(switchColor)
                .clickable {
                    animatedState.value = !animatedState.value

                }

        )
        Box(
            modifier = modifier
                .layoutId("textOff"),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "OFF", color = Color.White, fontSize = textOffFontSize.sp)
        }
        Box(
            modifier = modifier
                .layoutId("textOn"),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "ON", color = Color.White, fontSize = textOnFontSize.sp)
        }

    }


}

private fun startConstraints(
    parentWidth: Int,
    parentHeight: Int
): ConstraintSet {
    return ConstraintSet {
        val switch = createRefFor("switch")
        val background = createRefFor("background")
        val textOn = createRefFor("textOn")
        val textOff = createRefFor("textOff")

        constrain(background) {
            width = Dimension.value(parentWidth.dp)
            height = Dimension.value(parentHeight.dp)
        }
        constrain(switch) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
        constrain(textOff) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
        constrain(textOn) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
    }
}


private fun endConstraints(
    parentWidth: Int,
    parentHeight: Int
): ConstraintSet {
    return ConstraintSet {
        val switch = createRefFor("switch")
        val background = createRefFor("background")
        val textOn = createRefFor("textOn")
        val textOff = createRefFor("textOff")
        constrain(background) {
            width = Dimension.value(parentWidth.dp)
            height = Dimension.value(parentHeight.dp)
        }
        constrain(switch) {
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
        constrain(textOff) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
        constrain(textOn) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            width = Dimension.value((parentWidth * 0.5).dp)
            height = Dimension.value((parentHeight).dp)
        }
    }
}
