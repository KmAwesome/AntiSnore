package com.example.myapplication.playBack

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}