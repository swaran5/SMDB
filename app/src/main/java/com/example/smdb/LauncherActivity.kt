package com.example.smdb
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.laucher_activity)

        supportActionBar?.hide()

        Handler().postDelayed({
           val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },1800)
    }
}