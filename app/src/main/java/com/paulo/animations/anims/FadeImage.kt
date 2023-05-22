package com.paulo.animations.anims

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.paulo.animations.dynamicIsland.App
import com.paulo.animations.others.ProgressLoader
import com.paulo.animations.others.SwipeDismiss
import com.paulo.animations.others.Switch
import com.paulo.animations.others.TextChange
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Float.min


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SHowLoad() {
    val painter = rememberAsyncImagePainter("https://t3.ftcdn.net/jpg/02/95/44/22/360_F_295442295_OXsXOmLmqBUfZreTnGo9PREuAPSLQhff.jpg")
    val state = painter.state

    val transition by animateFloatAsState(
        targetValue = if (state is AsyncImagePainter.State.Success) 1f else 0f
    )
    val animation = rememberInfiniteTransition()
    val progress by animation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Restart,
        )
    )

val scope = CoroutineScope(Dispatchers.IO)
val visible = remember {
    mutableStateOf(false)
}
    if (state is AsyncImagePainter.State.Loading) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .scale(progress)
                .alpha(1f - progress)
                .border(
                    5.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
        )

    }
    scope.launch {
        delay(3000)
        visible.value = true
    }
    AnimatedVisibility(visible =visible.value) {
        Image(
            painter = painter,
            contentDescription = "custom transition based on painter state",
            modifier = Modifier
                .alpha(transition)
                .scale(.8f + (.2f * transition))
                .graphicsLayer { rotationX = (1f - transition) * 5f }
                .alpha(min(1f, transition / .2f))
        )
    }

}

@Composable
fun LoadingAnimation() {


  }