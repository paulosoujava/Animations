package com.paulo.animations.anims

import android.graphics.drawable.AnimatedImageDrawable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.animations.R

enum class BoxState {
    Collapsed,
    Expanded
}







@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Test() {
    var selected by remember { mutableStateOf(false) }
// Animates changes when `selected` is changed.
    val transition = updateTransition(selected)
    val borderColor by transition.animateColor(label = "") { isSelected ->
        if (isSelected) Color.Magenta else Color.White
    }
    val elevation by transition.animateDp(label = "") { isSelected ->
        if (isSelected) 1.dp else 12.dp
    }
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Card(
        modifier = Modifier.clickable {
            selected = !selected
        },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        elevation =  elevation
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            Text(text = "Hello, world!")
            // AnimatedVisibility as a part of the transition.
            transition.AnimatedVisibility(
                visible = { targetSelected -> targetSelected },
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {


                Text(text = "It is fine today.", color = color, fontSize = 20.sp)

            }
            // AnimatedContent as a part of the transition.
            transition.AnimatedContent { targetState ->
                if (targetState) {
                    Text(text = "Selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "Phone")
                }
            }
        }
    }

}

@Composable
fun Collapse() {
    //var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    var currentState = remember { MutableTransitionState(BoxState.Collapsed) }

    val transition = updateTransition(currentState)

    // Start in collapsed state and immediately animate to expanded

    Button(onClick = {
        if(currentState.targetState == BoxState.Collapsed)
            currentState.targetState = BoxState.Expanded
        else
            currentState.targetState = BoxState.Collapsed
    }) {
        Text(text = "GO")
    }



    val rect by transition.animateRect(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
            BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
        }
    }
    val borderWidth by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 0.dp
        }
    }
    val color by transition.animateColor(
        transitionSpec = {
            when {
                BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                    spring(stiffness = 50f)
                else ->
                    tween(durationMillis = 500)
            }
        }, label = ""
    ) { state ->
        when (state) {
            BoxState.Collapsed -> MaterialTheme.colors.primary
            BoxState.Expanded -> MaterialTheme.colors.background
        }
    }
    Box(modifier = Modifier
        .size(rect.width.dp, rect.height.dp)
        .border(borderWidth, color, RoundedCornerShape(10.dp))
        .background(color = color,)){
        Text(text = "GO", modifier = Modifier.padding(30.dp))
    }
}

@Composable
fun AnimateContentSize() {
    var message by remember { mutableStateOf("Hello") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .animateContentSize()
        ) {
            Text(text = message, fontSize = 34.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.size(30.dp))
        var currentPage by remember { mutableStateOf("B") }
        Button(onClick = {
            if(currentPage == "B") currentPage = "A" else currentPage = "B"
        }) {
            Text(text = "NAVIGATE", fontSize = 34.sp, fontWeight = FontWeight.Bold)
        }
        Crossfade(targetState = currentPage) { screen ->
            when (screen) {
                "A" -> Text("Page A", fontSize = 34.sp, fontWeight = FontWeight.Bold)
                "B" -> Text("Page B", fontSize = 34.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

}


@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun ExpandContent() {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using
                        SizeTransform { initialSize, targetSize ->
                            if (targetState) {
                                keyframes {
                                    // Expand horizontally first.
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // Shrink vertically first.
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Box(modifier = Modifier
                    .background(Color.Green)
                    .size(300.dp), contentAlignment = Alignment.Center) {
                    Column {
                        Text(text =
                        """lorem ipsum dolor sit amet, consectetur adipiscing elit
                                     lorem ipsum dolor sit amet, consectetur adipiscing elit 
                                    lorem ipsum dolor sit amet, consectetur adipiscing elit
                                    lorem ipsum dolor sit amet, consectetur adipiscing elit
                                """, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                Box(modifier = Modifier
                    .background(Color.Green)
                    .size(30.dp), contentAlignment = Alignment.Center) {
                    Icon( painterResource(id = com.paulo.animations.R.drawable.baseline_more_vert_24), contentDescription = null)
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MoreAnimation() {
    Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        var count by remember { mutableStateOf(0) }
        Row() {
            Button(onClick = { count++ }) {
                Text("Add")
            }
            Spacer(modifier = Modifier.width(100.dp))
            Button(onClick = { count-- }) {
                Text("Minus")
            }
        }


        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // Compare the incoming number with the previous number.
                if (targetState > initialState) {
                    // If the target number is larger, it slides up and fades in
                    // while the initial (smaller) number slides up and fades out.
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    // If the target number is smaller, it slides down and fades in
                    // while the initial number slides down and fades out.
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(text = "$targetCount", fontSize = 34.sp, fontWeight = FontWeight.Bold)
        }
    }
}