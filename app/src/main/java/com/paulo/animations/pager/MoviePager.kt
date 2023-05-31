package com.paulo.animations.pager

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MoviePager() {

    val horizontalState = rememberPagerState(initialPage = 0)

    Column {
        HorizontalPager(
            count = movies.size,
            modifier = Modifier
                .weight(.7f)
                .padding(
                    top = 32.dp
                ),
            state = horizontalState,
           // pageSpacing = 1.dp,
            //beyondBoundsPageCount = 9,
        ) { page ->
            Box(
                modifier = Modifier
                    .zIndex(page * 10f)
                    .padding(
                        start = 64.dp,
                        end = 32.dp,
                    )
                    .graphicsLayer {
                        val startOffset = horizontalState.currentPageOffset //tartOffsetForPage(page)
                        //translationX = size.width * (startOffset * .99f)

                        alpha = (2f - startOffset) / 2f

                        val blur = (startOffset * 20f).coerceAtLeast(0.1f)
                        renderEffect = RenderEffect
                            .createBlurEffect(
                                blur, blur, Shader.TileMode.DECAL
                            )
                            .asComposeRenderEffect()

                        val scale = 1f - (startOffset * .1f)
                        scaleX = scale
                        scaleY = scale
                    }
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        color = Color(0xFFF58133),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = movies[page].img,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .weight(.3f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            val verticalState = rememberPagerState()

            VerticalPager(
                count = movies.size,
                state = verticalState,
                modifier = Modifier
                    .weight(1f)
                    .height(72.dp),
                //userScrollEnabled = false,
                horizontalAlignment = Alignment.Start,
            ) { page ->
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = movies[page].title,
                        style = MaterialTheme.typography.h1.copy(
                            fontWeight = FontWeight.Thin,
                            fontSize = 28.sp,
                        )
                    )
                    Text(
                        text = movies[page].subtitle,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.Black.copy(alpha = .56f),
                        )
                    )
                }
            }

            LaunchedEffect(Unit) {
                snapshotFlow {
                    Pair(
                        horizontalState.currentPage,
                        horizontalState.currentPageOffset//currentPageOffsetFraction
                    )
                }.collect { (page, offset) ->
                    verticalState.scrollToPage(page, offset)
                }
            }


            val interpolatedRating by remember {
                derivedStateOf {
                    val position = horizontalState.currentPage//offsetForPage(0)
                    val from = floor(position.toDouble()).roundToInt()
                    val to = ceil(position.toDouble()).roundToInt()

                    val fromRating = movies[from].rating.toFloat()
                    val toRating = movies[to].rating.toFloat()

                    val fraction = position - position.toInt()
                    fromRating + ((toRating - fromRating) * fraction)
                }
            }

            RatingStars(rating = interpolatedRating)
        }
    }
}

@Composable
fun RatingStars(
    modifier: Modifier = Modifier,
    rating: Float,
) {
    Row(
        modifier = modifier
    ) {

        for (i in 1..5) {
            val animatedScale by animateFloatAsState(
                targetValue = if (floor(rating) >= i) {
                    1f
                } else if (ceil(rating) < i) {
                    0f
                } else {
                    rating - rating.toInt()
                },
                animationSpec = spring(
                    stiffness = Spring.StiffnessMedium
                ),
                label = ""
            )

            Box(
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Rounded.Star),
                    contentDescription = null,
                    modifier = Modifier.alpha(.1f),
                )
                Icon(
                    painter = rememberVectorPainter(image = Icons.Rounded.Star),
                    contentDescription = null,
                    modifier = Modifier.scale(animatedScale),
                    tint = Color(0xFFD59411)
                )
            }

        }

    }
}


data class Movie(
    val title: String = "Avengers",
    val subtitle: String = "",
    val rating: Int = 4,
    val img: String = "",
)

val movies = listOf(
    Movie(
        title = "Moonlight",
        subtitle = "Barry Jenkins • 2016",
        rating = 4,
        img = "https://www.themoviedb.org/t/p/w1280/4911T5FbJ9eD2Faz5Z8cT3SUhU3.jpg",
    ),
    Movie(
        title = "Little Miss Sunshine",
        subtitle = "Dayton & Faris • 2006",
        rating = 5,
        img = "https://www.themoviedb.org/t/p/w1280/tFnTds88mCuLcLPBseK1kF2E3qv.jpg",
    ),
    Movie(
        title = "The Lobster",
        subtitle = "Yorgos Lanthimos • 2015",
        rating = 2,
        img = "https://www.themoviedb.org/t/p/w1280/7Y9ILV1unpW9mLpGcqyGQU72LUy.jpg",
    ),
    Movie(
        title = "Her",
        subtitle = "Spike Jonze • 2013",
        rating = 4,
        img = "https://www.themoviedb.org/t/p/w1280/eCOtqtfvn7mxGl6nfmq4b1exJRc.jpg",
    ),
    Movie(
        title = "Memento",
        subtitle = "Christopher Nolan • 2000",
        rating = 3,
        img = "https://www.themoviedb.org/t/p/w1280/yuNs09hvpHVU1cBTCAk9zxsL2oW.jpg",
    ),
    Movie(
        title = "The Room",
        subtitle = "Tommy Wiseau • 2003",
        rating = 1,
        img = "https://www.themoviedb.org/t/p/w1280/9QscHN4pXj6Ja1k7e1ZT4vWDGnr.jpg",
    ),
)