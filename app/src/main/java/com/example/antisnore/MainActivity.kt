package com.example.antisnore

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.antisnore.playBack.AndroidAudioPlayer
import com.example.antisnore.record.AndroidAudioRecorder
import com.example.antisnore.ui.theme.MyApplicationTheme
import java.io.File

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                GetRecorderPermission()

                TipTimePreview()
            }
        }
    }
}

@Preview()
@Composable
fun TipTimePreview() {

    var context = LocalContext.current

    val recorder by lazy {
        AndroidAudioRecorder(context)
    }

    val player by lazy {
        AndroidAudioPlayer(context)
    }

    TipTime(context, recorder, player)
}

@Composable
fun TipTime(
    applicationContext: Context,
    recorder: AndroidAudioRecorder,
    player: AndroidAudioPlayer
) {
    var audioFile: File?= null

    var db by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            File(applicationContext.cacheDir,"audio.mp3").also {
                recorder.start(it)
                audioFile = it
            }
        }) {
            Text(text = "Start Recording")
        }
        Button(onClick = {
            recorder.stop()
        }) {
            Text(text = "Stop Recording")
        }
        Button(onClick = {
            player.playFile(audioFile ?: return@Button)
        }) {
            Text(text = "Play")
        }
        Button(onClick = {
            player.stop()
            db = recorder.getdB()
        }) {
            Text(text = "Stop Playing ")
        }
        Text(
            text = "count $db"
        )
    }

}


@Composable
fun GetRecorderPermission() {

    var isRecorderPmissionGranted by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(), onResult = { isGranted ->
        if (isGranted) {
            Log.d("TAG", "isGranted")
            isRecorderPmissionGranted = true
        }
    })

    Column {
        Button(onClick = {
            if (!isRecorderPmissionGranted) {
                launcher.launch(Manifest.permission.RECORD_AUDIO)
            }
        }) {
            Text(text = "Permission $isRecorderPmissionGranted")
        }
    }

}


