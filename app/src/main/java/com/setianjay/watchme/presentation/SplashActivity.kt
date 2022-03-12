package com.setianjay.watchme.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenProcess()
    }

    /**
     * splash screen process
     * */
    private fun splashScreenProcess() {
        lifecycleScope.launch {
            delay(3000L)
            Intent(this@SplashActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}