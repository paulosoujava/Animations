package com.paulo.animations.cripto


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object MyCripto {
    var cont = 0
}

@Composable
fun EmailInput() {
    var startTime by remember { mutableStateOf(0L) }
    var typingTime by remember { mutableStateOf(0L) }
    var email by remember { mutableStateOf("") }

    val lifecycleOwner = LocalLifecycleOwner.current
val bloq = remember {
    mutableStateOf(false)
}
    LaunchedEffect(key1 = lifecycleOwner) {
        lifecycleOwner.lifecycleScope.launch {
            while (true) {
                delay(1000) // Verifica o tempo a cada 1 segundo (1000ms)
                val currentTime = System.currentTimeMillis()
                if (startTime != 0L) {
                    typingTime = currentTime - startTime
                }
            }
        }


    }
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
       AnimatedVisibility(visible = bloq.value) {
           Text(text = "BLOQUEADO", fontSize = 34.sp, color = Color.White, fontWeight = FontWeight.Bold)
       }

        AnimatedVisibility(visible = !bloq.value) {
            Column {

                BasicTextField(
                    modifier = Modifier
                        .height(30.dp)
                        .fillMaxWidth()
                        .background(color = Color.White),
                    value = email, // O valor do campo de texto
                    onValueChange = {
                        email = it
                        if (startTime == 0L) {
                            startTime = System.currentTimeMillis()
                        }
                    }
                )
                Button(onClick = {
                    MyCripto.cont++
                    if (MyCripto.cont == 3) {
                        bloq.value = true
                    }
                    email = ""
                    Log.d("TIME", "TEMPO == $typingTime")
                }) {
                    Text("Enviar")
                }
            }
        }
    }

}


