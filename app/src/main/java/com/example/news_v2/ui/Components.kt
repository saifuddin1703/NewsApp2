package com.example.news_v2.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.progressindicator.CircularProgressIndicator

@Composable
fun CircularProgressBar(modifier : Modifier = Modifier){
    val infiniteTransition = rememberInfiniteTransition()

    val startAngle by infiniteTransition.animateFloat(
        initialValue = -90f,
        targetValue = 270f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1000,easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    Canvas(modifier = modifier, onDraw = {
        drawArc(
            color = Color.Blue,
            startAngle = startAngle,
            sweepAngle = 90f,
            useCenter = false,
            style = Stroke(width = 5.dp.toPx(),cap = StrokeCap.Round),
            topLeft = Offset(x = 5.dp.toPx(),y = 5.dp.toPx()),
            size = Size(size.width - 10.dp.toPx(),size.height - 10.dp.toPx())
        )
    })
}

@Composable
@Preview
fun ProgressBarPreview(){
    CircularProgressBar()
}