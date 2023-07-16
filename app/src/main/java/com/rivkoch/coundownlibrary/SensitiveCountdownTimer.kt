package com.rivkoch.coundownlibrary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SensitiveCountdownTimer(minutes: Double) {
    val viewModel: com.rivkoch.countdown_timer_setup.SensitiveCountdownTimerViewModel = viewModel()

    Column(modifier = Modifier.padding(16.dp)) {
        val displayMinutes = viewModel.timerValue / 60
        val displaySeconds = viewModel.timerValue % 60
        Text(
            text = String.format("%02d:%02d", displayMinutes, displaySeconds),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )
        if (!viewModel.isTimerRunning) {
            Button(
                onClick = {
                    viewModel.startTimer(minutes)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Start")
            }
        } else {
            if (viewModel.isPaused) {
                Button(
                    onClick = {
                        viewModel.resumeTimer()
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Resume")
                }
            } else {
                Button(
                    onClick = {
                        viewModel.pauseTimer()
                    },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Pause")
                }
            }
            Button(
                onClick = {
                    viewModel.stopTimer()
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Stop")
            }
            Button(
                onClick = {
                    viewModel.resetTimer()
                },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Reset")
            }
        }
    }
}

@Preview
@Composable
fun PreviewSensitiveCountdownTimer() {
    SensitiveCountdownTimer(1.0)
}
