package com.paulo.animations.anims

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.paulo.animations.R

@OptIn(ExperimentalMotionApi::class)
@Composable
 fun youtubePlayer(context: Context) {
    var animateEnd by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animateEnd) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        motionScene = MotionScene(
            content = context.resources
                .openRawResource(R.raw.scene4)
                .readBytes()
                .decodeToString()
        ),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val properties = motionProperties(id = "content_video")
        val iconProperties = motionProperties(id = "icon_picture_in_picture")


        Box(
            modifier = Modifier
                .layoutId("content_video")
                .background(properties.value.color("background"))
        )
        Box(
            modifier = Modifier
                .layoutId("video_view")
                .background(Color.Black)

        )
        Text(
            text = "title video ", modifier = Modifier
                .layoutId("content_title"),
            color =  Color.White
        )
        Text(
            text = "subititle video ", modifier = Modifier
                .layoutId("content_subtitle"),
            color = Color.White
        )
        IconButton(
            onClick = {
                animateEnd = !animateEnd
            },
            modifier = Modifier
                .layoutId("icon_picture_in_picture"),
        ) {
            Log.d("ICON", "ICON ${iconProperties.value.int("up")}")
            Icon(
                painter = painterResource(
                    id = if (iconProperties.value.int("up") > 1)
                        R.drawable.baseline_picture_in_picture
                    else
                        R.drawable.baseline_picture_in_picture_24
                ), contentDescription = null,
                tint = iconProperties.value.color("background")
            )
        }
        Box(
            modifier = Modifier
                .layoutId("list_video")

        ) {
            LazyColumn {
                items(10) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color.Black)
                        ) {
                            Spacer(modifier = Modifier.size(30.dp, 80.dp))
                        }
                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .weight(2f)
                        ) {
                            Text(text = "Title")
                            Text(text = "Subtitle")
                        }
                    }

                }
            }
        }
    }
}
