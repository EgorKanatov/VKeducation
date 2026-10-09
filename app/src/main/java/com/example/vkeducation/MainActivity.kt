
package com.example.vkeducation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var text by remember { mutableStateOf("") }
            var error by remember { mutableStateOf("") }

            MaterialTheme {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text("Практическое задание 3")

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = text,
                        onValueChange = {
                            text = it
                            error = ""
                        },
                        label = { Text("Введите текст или номер") }
                    )

                    if (error.isNotEmpty()) {
                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(onClick = {
                        if (text.isBlank()) {
                            error = "Введите текст"
                        } else {
                            val intent = Intent(
                                this@MainActivity,
                                SecondActivity::class.java
                            )
                            intent.putExtra("text", text)
                            startActivity(intent)
                        }
                    }) {
                        Text("Открыть вторую Activity")
                    }

                    Button(onClick = {
                        val phone = text.trim()

                        if (phone.matches(Regex("""\+?[0-9]{10,15}"""))) {
                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:$phone")
                            )
                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            } else {
                                error = "Приложение для звонков не найдено"
                            }
                        } else {
                            error = "Введите правильный номер"
                        }
                    }) {
                        Text("Позвонить другу")
                    }

                    Button(onClick = {
                        if (text.isBlank()) {
                            error = "Введите текст"
                        } else {
                            val intent = Intent(Intent.ACTION_SEND)
                            intent.type = "text/plain"
                            intent.putExtra(Intent.EXTRA_TEXT, text)

                            startActivity(
                                Intent.createChooser(
                                    intent,
                                    "Поделиться через..."
                                )
                            )
                        }
                    }) {
                        Text("Поделиться текстом")
                    }
                }
            }
        }
    }
}
