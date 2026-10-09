package com.example.vkeducation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = intent.getStringExtra("text") ?: ""

        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("Вторая Activity")

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("Полученный текст: $text")
                }
            }
        }
    }
}
