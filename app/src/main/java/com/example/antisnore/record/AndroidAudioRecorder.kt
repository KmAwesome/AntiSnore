package com.example.antisnore.record

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.io.File
import java.io.FileOutputStream


class AndroidAudioRecorder (private val context: Context) {
    private var recorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var db = 100
    private lateinit var  handler: Handler
    private lateinit var runnable: Runnable

    fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        }else MediaRecorder()
    }

    fun start(outputFile: File) {
        recorder = createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)
            prepare()
            start()
        }

        handler = Handler(Looper.getMainLooper())

        runnable = object : Runnable {
            override fun run() {
                db = recorder!!.maxAmplitude
                handler.postDelayed(this, 5000)
                Log.i("TAG", "run: " + db)
            }
        }
        handler.post(runnable)
    }

    fun stop() {
        handler.removeCallbacks(runnable)
        recorder?.stop()
        recorder?.reset()
        recorder = null
    }

    fun getdB(): Int{
        return db
    }





}
