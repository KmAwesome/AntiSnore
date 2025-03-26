package com.example.antisnore.playBack

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}