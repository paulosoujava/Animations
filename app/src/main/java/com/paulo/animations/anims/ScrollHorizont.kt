package com.paulo.animations.anims

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

val listOfImages = listOf(
    "https://img.freepik.com/free-photo/front-view-funny-cute-dog-concept_23-2148786532.jpg",
    "https://img.freepik.com/free-photo/front-view-funny-cute-dog-concept_23-2148786532.jpg",
    "https://img.freepik.com/free-photo/front-view-funny-cute-dog-concept_23-2148786532.jpg",
    "https://img.freepik.com/free-photo/front-view-funny-cute-dog-concept_23-2148786532.jpg",
    "https://img.freepik.com/free-photo/front-view-funny-cute-dog-concept_23-2148786532.jpg",

    )

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HList() {
    val pagerState = rememberPagerState()
    val matrix = remember {
        ColorMatrix()
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 48.dp)
    ) {
        HorizontalPager(count = listOfImages.size, state = pagerState) { index ->
            val pageOffset = (pagerState.currentPage - index) + pagerState.currentPageOffset
            val imageSize by animateFloatAsState(
                targetValue = if (pageOffset != 0.0f) 0.75f else 1f,
                animationSpec = tween(durationMillis = 300)
            )
            LaunchedEffect(key1 = imageSize, block = {
                if(pageOffset != 0.0f){
                    matrix.setToSaturation(0f)
                }else{
                    matrix.setToSaturation(1f)
                }
            })
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    }
                    .clip(RoundedCornerShape(16.dp)),
                model = ImageRequest.Builder(LocalContext.current).data(listOfImages[index]).build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter =  ColorFilter.colorMatrix(matrix)
            )

        }
    }
}