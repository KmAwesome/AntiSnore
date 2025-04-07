package com.example.antisnore

import android.os.Bundle
import android.widget.SeekBar
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

class TestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
fun DbDisplay(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth(1f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(50.dp)
                .drawBehind {
                    drawCircle(
                        color = Color.LightGray,
                        radius = this.size.maxDimension
                    )
                },
            text = "dB Meter",
        )
    }

}

@Composable
fun SliderBarRow(modifier: Modifier = Modifier) {

}

@Composable
fun SliderBar(modifier: Modifier = Modifier) {

    var sliderPosition by remember { mutableFloatStateOf(0.3f) }

    Row(
        modifier = Modifier.fillMaxWidth(1f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = modifier
                .size(50.dp)
                .padding(10.dp),
            imageVector = Icons.Filled.DateRange,
            contentDescription = ""
        )
        Slider(
            modifier = Modifier
                .weight(1f),
            value = sliderPosition,
            onValueChange = {sliderPosition = it},
        )
        Text(
            modifier = Modifier
                .width(50.dp),
            text = (sliderPosition * 100).roundToInt().toString(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun StartButton(modifier: Modifier = Modifier) {

}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun DbDisplayPreview() {
    DbDisplay()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SliderBarRowPreview(modifier: Modifier = Modifier) {
    SliderBarRow()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SliderBarPreview(modifier: Modifier = Modifier) {
    SliderBar()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun StartButtonPreview() {
    StartButton()
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun MainScreenPreview() {
    MainScreen()
}




