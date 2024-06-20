package com.example.officialtoobar.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.officialtoobar.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var SplashTime: Any
    private val splashTime: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        //Gerando um atraso com Corutine
        CoroutineScope(Dispatchers.Main).launch {
            delay(SplashTime)

            val intent = Intent( this@SplashActivity, ListagemAlunoActivity::class.java)
            startActivity(intent)
            finish()
        }

     /*   //Splash- primeira forma
        Handler().postDelayed({
            val intent = Intent(this, ListagemAlunoActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) */
    }

    private fun delay(splashTime: Any) {

    }
}