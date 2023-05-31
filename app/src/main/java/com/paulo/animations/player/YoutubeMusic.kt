package com.paulo.animations.player

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CastConnected
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paulo.animations.R
import com.paulo.animations.anims.AnimatedText
import com.paulo.animations.anims.VideoPlayingAnimation
import com.paulo.animations.other3.Five
import com.paulo.animations.other3.Notification
import com.paulo.animations.others.TextChange
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ExperimentalToolbarApi
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.time.format.TextStyle

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun YoutubePlayer() {


    BottomSheetScaffold(sheetContent = {

    }) {
    ParallaxEffect()
    }
}


@Composable
fun ParallaxEffect() {
    val state = rememberCollapsingToolbarScaffoldState()
    val offsetY = state.offsetY // y offset of the layout
    val progress = state.toolbarState.progress //
    var enabled by remember { mutableStateOf(true) }

    Box {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(Color.Black),
            enabled = enabled,
            toolbar = {
                // Collapsing toolbar collapses its size as small as the that of
                // a smallest child. To make the toolbar collapse to 50dp, we create
                // a dummy Spacer composable.
                // You may replace it with TopAppBar or other preferred composable.
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.alok),
                    modifier = Modifier
                        .parallax(0.5f)
                        .fillMaxWidth()
                        .height(300.dp)
                        .graphicsLayer {
                            // change alpha of Image as the toolbar expands
                            alpha = state.toolbarState.progress
                        },
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )

                /*Image(
                    painter = painterResource(id = R.drawable.alok),
                    contentDescription = null,
                    modifier = Modifier.size((38 + (30 - 18) * progress).dp,))*/

            }
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.End
                        ) {
                            VideoPlayingAnimation()
                        }
                        /*AnimatedText(
                            text = "Enable collapse/expand",
                            useAnimation = true,
                            style = MaterialTheme.typography.h3,
                            //fontSize = (18 + (30 - 18) * progress).sp,
                                    modifier = Modifier . padding (20.dp)
                        )*/
                        TextChange(41.sp)
                    }

                }
                items(
                    List(100) { "Hello World!! $it" }
                ) {
                    Box(modifier = Modifier.padding(10.dp)) {
                        Notification()
                    }

                }
            }

        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White)
            }
            Text(
                "Enable collapse/expand",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.weight(2f)
            )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Share, contentDescription = null, tint = Color.White)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.CastConnected, contentDescription = null, tint = Color.White)
            }
        }
    }
}