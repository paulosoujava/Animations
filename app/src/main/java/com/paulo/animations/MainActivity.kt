package com.paulo.animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.animations.anims.AnimatedText
import com.paulo.animations.anims.Profile


import com.paulo.animations.ui.theme.AnimationsTheme

enum class States {
    EXPANDED,
    COLLAPSED
}

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMotionApi::class, ExperimentalAnimationGraphicsApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val context = LocalContext.current
            val state = remember {
                mutableStateOf(true)
            }
            AnimationsTheme {
                // A surface container using the 'background' color from the theme
              //  MoreExample()
                Profile()

            }
        }
    }
}

@Composable
fun MoreExample() {
    var visible by remember {
        mutableStateOf(true)
    }
    var isRound by remember {
        mutableStateOf(false)
    }
    val borderRadius by animateIntAsState(
        targetValue = if (isRound) 100 else 0,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    val transition = updateTransition(targetState = borderRadius, label = "")
    val sizeIcon by transition.animateInt(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            )
        },
        label = "",
        targetValueByState = { isRound ->
            if (isRound > 0) 50 else 0
        }
    )
    val sizeIcon2 by transition.animateInt(
        transitionSpec = {
            tween(
                durationMillis = 300,
                delayMillis = 100,
                easing = FastOutLinearInEasing
            )
        },
        label = "",
        targetValueByState = { isRound ->
            if (isRound > 0) 0 else 50
        }
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Button(onClick = {
                isRound = !isRound
            }) {
                Text(text = "Circle")
            }

            Button(onClick = {
                visible = !visible
            }) {
                Text(text = "Visible")
            }
        }
        AnimatedText(
            text = "Animated Text",
            useAnimation = visible,
        )
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(borderRadius))
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_share_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(sizeIcon.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_vert_24),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(sizeIcon2.dp)
                )
            }
        }


    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
private fun motionExample1() {
    val context = LocalContext.current

    var progress by remember { mutableStateOf(0f) }

    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.scene9)
            .readBytes()
            .decodeToString()
    }
    Column {
        MotionLayout(
            motionScene = MotionScene(content = motionScene),
            progress = if (progress < 0.5) 1 - progress * 2 else progress * 2 - 1,
            //transitionName = if (progress<0.5f) "part1" else "part2",
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
                    .layoutId("row_container")
            ) { }
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topEnd = 25.dp,
                            bottomEnd = 25.dp,
                            topStart = 25.dp,
                            bottomStart = 25.dp
                        )
                    )
                    .background(Color.Red)
                    .layoutId("circle")
            )
        }

        Spacer(Modifier.height(32.dp))

        Slider(
            value = progress,
            onValueChange = {
                progress = it
            }
        )
    }

}
