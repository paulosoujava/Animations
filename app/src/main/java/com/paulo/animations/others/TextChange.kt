@file:OptIn(ExperimentalAnimationApi::class)
package com.paulo.animations.others

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.Button
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val original = "Dj Alloc"
const val translated = "AlloC"

@Composable
fun TextChange(
    fontSize: TextUnit = 20.sp
) {
    var text by remember {
        mutableStateOf(original)
    }
    val scope = CoroutineScope(Dispatchers.IO)

    SideEffect {
        scope.launch {
            delay(300)
           // text = if (text == original) translated else original
        }
    }

    val time = 1000

    val blur = remember { Animatable(0f) }

    LaunchedEffect(text) {
        blur.animateTo(30f, tween(time / 2, easing = LinearEasing))
        blur.animateTo(0f, tween(time / 2, easing = LinearEasing))
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        MetaContainer(
            modifier = Modifier
                .animateContentSize()
                .clipToBounds()
                .fillMaxWidth(),
            cutoff = .2f
        ) {
            MetaEntity2(
                modifier = Modifier.fillMaxWidth(),
                blur = blur.value,
                metaContent = {
                    AnimatedContent(
                        targetState = text,
                        modifier = Modifier
                            .fillMaxWidth(),
                        transitionSpec = {
                            fadeIn(tween(time, easing = LinearEasing)) + expandVertically(
                                tween(
                                    time,
                                    easing = LinearEasing
                                ), expandFrom = Alignment.CenterVertically
                            ) with fadeOut(
                                tween(
                                    time,
                                    easing = LinearEasing
                                )
                            ) + shrinkVertically(
                                tween(
                                    time,
                                    easing = LinearEasing
                                ), shrinkTowards = Alignment.CenterVertically
                            )
                        }
                    ) { text ->
                        Text(
                            text,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = fontSize,
                            textAlign = TextAlign.Center,
                        )
                    }
                }) {}
        }
        /*Button(onClick = {
            text = if (text == original) translated else original
        }) {
            Text("+1.69%")
        }*/
    }
}