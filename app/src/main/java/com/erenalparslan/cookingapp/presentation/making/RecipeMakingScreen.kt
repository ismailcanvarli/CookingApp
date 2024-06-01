package com.erenalparslan.cookingapp.presentation.making

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.erenalparslan.cookingapp.presentation.cook.CookViewModel

@Composable
fun RecipeMakingScreen(navHostController: NavHostController) {
    val viewModel = hiltViewModel<MakingViewModel>()
    val state by viewModel.cookState.collectAsState()
    var currentStep by remember { mutableStateOf(0) }
    var timerValue by remember { mutableStateOf("00:00") }
    var timeRemaining by remember { mutableStateOf(0L) }
    var timerRunning by remember { mutableStateOf(false) }
    var timer: CountDownTimer? by remember { mutableStateOf(null) }
    var showDialog by remember { mutableStateOf(false) }
    var aiResponse by remember { mutableStateOf("") }

    fun startTimer(time: Long) {
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                timerValue = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timerValue = "00:00"
                timerRunning = false
            }
        }.start()
    }



    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
    if (state.isError) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(text = "Sorry,an error occurred")
        }
    }

    state.list?.let {


        LaunchedEffect(currentStep) {
            if (!it[currentStep]?.time.isNullOrEmpty()) {
                Log.d("Erenss", "RecipeMakingScreen: ${it.toString()}")
                val timeString = it[currentStep]?.time.toString()
                Log.d("Erenss", "RecipeMakingScreen: ${timeString}")
                timeRemaining = timeString.split(" ")[0].toLong() * 1000 * 60
                Log.d("Erenss", "RecipeMakingScreen: ${timeRemaining}")
                val minutes = (timeRemaining / 1000) / 60
                val seconds = (timeRemaining / 1000) % 60
                timerValue = String.format("%02d:%02d", minutes, seconds)
            }
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Şu anki aşamayı gösteren metin
            Text(
                text = it[currentStep]?.instruction ?: "",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )


            if (!it[currentStep]?.time.isNullOrEmpty()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = timerValue,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )

                    Button(onClick = {
                        if (timerRunning) {
                            timer?.cancel()
                        } else {
                            startTimer(timeRemaining)
                        }
                        timerRunning = !timerRunning
                    }) {
                        Text(text = if (timerRunning) "Durdur" else "Başlat")
                    }
                }
            }

            // AI açıklama pop-up
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = {
                        Text(text = "Yapay Zeka Açıklaması")
                    },
                    text = {
                        Text(text = state.question.toString())
                    },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text(text = "Kapat")
                        }
                    }
                )
            }

            Button(onClick = {
                showDialog = true
                viewModel.askGemini(it[currentStep]?.instruction)
            }) {
                Text(text = "Yapay zeka ile açıkla")
            }

            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        // Geri butonuna tıklandığında bir önceki aşamaya geç
                        if (currentStep > 0) currentStep--
                    }, modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    Text(text = "Geri")
                }
                Button(
                    onClick = {
                        // İleri butonuna tıklandığında bir sonraki aşamaya geç
                        it?.let {
                            if (currentStep < it.lastIndex) currentStep++ else {
                                navHostController.popBackStack()
                            }
                        }

                    }, modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(text = "İleri")
                }
            }
        }
    }

}