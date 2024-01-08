package com.example.hilos

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var contador = 0
    private var isRunning = false
    private lateinit var handler: Handler
    private lateinit var contadorTextView: TextView
    private lateinit var iniciarButton: Button
    private lateinit var pausarButton: Button
    private lateinit var reiniciarButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(Looper.getMainLooper())
        contadorTextView = findViewById(R.id.contadorTextView)
        iniciarButton = findViewById(R.id.iniciarButton)
        pausarButton = findViewById(R.id.pausarButton)
        reiniciarButton = findViewById(R.id.reiniciarButton)

        iniciarButton.setOnClickListener {
            if (!isRunning) {
                iniciarContador()
                showToast("Hilo iniciado")
            }
        }

        pausarButton.setOnClickListener {
            if (isRunning) {
                pausarContador()
                showToast("Hilo pausado")
            }
        }

        reiniciarButton.setOnClickListener {
            reiniciarContador()
            showToast("Hilo reiniciado")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun iniciarContador() {
        isRunning = true
        Thread {
            while (isRunning) {
                try {
                    Thread.sleep(1000)
                    contador++
                    handler.post {
                        actualizarContador()
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    private fun pausarContador() {
        isRunning = false
    }

    private fun reiniciarContador() {
        contador = 0
        actualizarContador()
        isRunning = false
    }

    private fun actualizarContador() {
        contadorTextView.text = contador.toString()
    }
}



