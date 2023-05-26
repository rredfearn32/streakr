package com.example.streakr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.streakr.ui.theme.StreakrTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val startTime = System.currentTimeMillis()
        setContent {
            StreakrTheme {
                StreakTimer(startTime)
            }
        }
    }
}

@Composable
fun StreakTimer(startTime: Long, modifier: Modifier = Modifier) {
    var isTimerRunning by remember {
        mutableStateOf(true)
    }
    var timerInSeconds by remember {
        mutableStateOf<Long>(0)
    }

    fun toggleTimer(isStart: Boolean) {
        isTimerRunning = isStart;
        timerInSeconds = if (isStart) {
            0
        } else {
            Date().time / 1000
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.primary
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = (timerInSeconds / 1000).toString(),
                fontSize = 72.sp,
                fontWeight = FontWeight.Black
            )
            Text(
                text = "Day Streak", fontSize = 22.sp
            )
            Row() {
                if (isTimerRunning) {
                    Button(onClick = { toggleTimer(false) }) {
                        Text(
                            text = "Stop", fontSize = 21.sp
                        )
                    }
                } else {
                    Button(onClick = { toggleTimer(true) }) {
                        Text(
                            text = "Start", fontSize = 21.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StreakrTheme {
        StreakTimer(5)
    }
}