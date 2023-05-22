package com.paulo.animations.dynamicIsland

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.paulo.animations.dynamicIsland.island.DynamicIsland
import com.paulo.animations.dynamicIsland.island.IslandState


@Composable
fun App() {
    Column {
        var islandState: IslandState by remember { mutableStateOf(IslandState.DefaultState()) }

        DynamicIsland(islandState = islandState)

        RadioButtonRow(
            text = "Default",
            selected = islandState is IslandState.DefaultState
        ) {
            islandState = IslandState.DefaultState()
        }

        RadioButtonRow(
            text = "Call state",
            selected = islandState is IslandState.CallState
        ) {
            islandState = IslandState.CallState()
        }

        RadioButtonRow(
            text = "Call Timer state",
            selected = islandState is IslandState.CallTimerState
        ) {
            islandState = IslandState.CallTimerState()
        }

        RadioButtonRow(
            text = "Face unlock state",
            selected = islandState is IslandState.FaceUnlockState
        ) {
            islandState = IslandState.FaceUnlockState()
        }
    }
}