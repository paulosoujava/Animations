package com.paulo.animations


import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.paulo.animations.other3.Five
import com.paulo.animations.other3.GptTest
import com.paulo.animations.other3.MoreExample
import com.paulo.animations.other3.Notification
import com.paulo.animations.pager.CircleRevealPager
import com.paulo.animations.pager.CubePager
import com.paulo.animations.pager.MoviePager
import com.paulo.animations.player.YoutubePlayer
import com.paulo.animations.qrcode.QrMain
import com.paulo.animations.ui.theme.AnimationsTheme

import com.paulo.animations.video.VideoPlayer


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            AnimationsTheme {
               //QrMain()
              //  YoutubePlayer()
                //CubePager()
                //CircleRevealPager()
                MoviePager()
            }

        // QrMain()

        /*   Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {




          //      Notification()
                //GptTest()
                //MoreExample()
               //  Spacer( modifier = Modifier.height(20.dp))
             //    Five()
                //ShowBoxAnimate(ctx = LocalContext.current)
                //ShowOthers()
                //TextChange()
                //Switch()
                //SwipeDismiss()
                //ProgressLoader()
                //ShowAnim()
                //ExpandableFAB()
                //ScaffoldLibrary()
                //ShowMain2()
                //StarbucksDemo()
                //CardDemoComposable()
                //App()
                // youtubePlayer(LocalContext.current)
                //VideoPlayingAnimation()
                //Vector()
                //ContentView()
                 //ShowOtherAnimations()
                //HList()
                // Profile()
                 //Test()
                //SHowLoad()
                //ShowConstraints()
                // CollapseScreenDemo()
                // ShowCardActionsProfile(context = LocalContext.current)
                //ShowCardActions(context = LocalContext.current)
                //ShowBox(context = LocalContext.current)
                //AnimatedText(text = "PAULO OLIVEIRA")
               // com.paulo.animations.Actions.App()
            }*/
        }

    }
}

