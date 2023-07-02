package com.rivkoch.countdown_timer_setup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class CountdownViewModel @Inject constructor() : ViewModel() {

    private var runCountdownJob: Job? = null
    private var resumeCountdownJob: Job? = null
    private var pausedTime: Int by mutableStateOf(0)

    var totalSeconds: Int by mutableStateOf(0)
        private set

    var timerValue by mutableStateOf(0)
        private set

    var isTimerRunning by mutableStateOf(false)
        private set

    var isPaused by mutableStateOf(false)
        private set

    private var isResumed by mutableStateOf(false)

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun startTimer(minutes: Double) {
        stopTimer()
        timerValue = (minutes * 60).toInt() // Set the timer value to the provided minutes
        totalSeconds = timerValue
        isTimerRunning = true

        runCountdownJob = coroutineScope.launch {
            for (i in totalSeconds downTo 0) {
                if (!isTimerRunning) continue
                if (!isPaused) {
                    timerValue = i
                }
                delay(1000L)
            }
        }
    }

    fun stopTimer() {
        isResumed = false
        isTimerRunning = false
        pauseJobs()
    }

    private fun pauseJobs() {
        resumeCountdownJob?.cancel()
        runCountdownJob?.cancel()
    }

    fun pauseTimer() {
        if (isTimerRunning && !isPaused) {
            isPaused = true
            isResumed = false

            pauseJobs()

            pausedTime = timerValue
        }
    }

    fun resumeTimer() {
        if (isPaused) {
            isPaused = false
            isResumed = true

            //continue job
            resumeCountdownJob = coroutineScope.launch {
                for (i in pausedTime downTo 0) {
                    if (!isTimerRunning) continue
                    if (!isPaused) {
                        timerValue = i
                    }
                    delay(1000L)
                }
            }
        }
    }

    fun resetTimer() {
        isResumed = false
        stopTimer()
        timerValue = totalSeconds
        isPaused = false
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
    }
}
