package com.kingo.voceslol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickevent(v: View) {
        var button = v as Button;
        var intent = Intent(this, VoicesActivity::class.java)
        intent.putExtra("champ", button.text)
        startActivity(intent)
        Toast.makeText(
            applicationContext,
            "Como en los viejos tiempos, dulce, simple y sin control:" + button.text,
            Toast.LENGTH_LONG
        ).show()
    }
}