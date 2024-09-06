package com.example.todoapp.ui.common

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun <T> DismissibleItem(
    item: T,
    onDismiss: (String) -> Unit,
    idSelector: (T) -> String,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val isDismissed = remember { mutableStateOf(false) }
    val dismissThreshold = 200.dp

    if (isDismissed.value) {
        return
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(16.dp))
    ) {
        DismissBackground(
            modifier = Modifier.matchParentSize(),
        )

        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(
                    state = rememberDraggableState { delta ->
                        val newOffsetX = offsetX + delta
                        offsetX = if (newOffsetX <= 0f) {
                            maxOf(newOffsetX, -dismissThreshold.value)
                        } else {
                            0f
                        }
                        if (offsetX <= -dismissThreshold.value) {
                            isDismissed.value = true
                            onDismiss(idSelector(item))
                        }
                    },
                    orientation = Orientation.Horizontal,
                    onDragStopped = {
                        if (isDismissed.value) return@draggable
                        offsetX = 0f
                    }
                )
        ) {
            content()
        }
    }
}


@Composable
fun DismissBackground(
    modifier: Modifier = Modifier,
    color: Color = Color.Red
) {
    Box(
        modifier = modifier
            .background(color)
            .padding(end = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete",
            tint = Color.White,
            modifier = Modifier.scale(0.8f)
        )
    }
}