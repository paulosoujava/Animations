package com.paulo.animations


import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.paulo.animations.fromGit.StarbucksDemo
import com.paulo.animations.ui.theme.AnimationsTheme
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.paulo.animations.anims.AnimatedText
import com.paulo.animations.anims.CollapseScreenDemo
import com.paulo.animations.anims.ContentView
import com.paulo.animations.anims.HList
import com.paulo.animations.anims.Profile
import com.paulo.animations.anims.SHowLoad
import com.paulo.animations.anims.ShowBox
import com.paulo.animations.anims.ShowCardActions
import com.paulo.animations.anims.ShowCardActionsProfile
import com.paulo.animations.anims.ShowConstraints
import com.paulo.animations.anims.ShowOtherAnimations
import com.paulo.animations.anims.Test
import com.paulo.animations.anims.Vector
import com.paulo.animations.anims.VideoPlayingAnimation
import com.paulo.animations.anims.youtubePlayer
import com.paulo.animations.boxes.ShowBoxAnimate
import com.paulo.animations.dynamicIsland.App
import com.paulo.animations.fromGit.CardDemoComposable
import com.paulo.animations.island2.ShowMain2
import com.paulo.animations.other3.Five
import com.paulo.animations.others.ExpandableFAB
import com.paulo.animations.others.ProgressLoader
import com.paulo.animations.others.ScaffoldLibrary
import com.paulo.animations.others.ShowAnim
import com.paulo.animations.others.SwipeDismiss
import com.paulo.animations.others.Switch
import com.paulo.animations.others.TextChange
import com.paulo.animations.others1.ShowOthers


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class, ExperimentalMotionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Column {
                Five()
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
            }
        }

    }
}

