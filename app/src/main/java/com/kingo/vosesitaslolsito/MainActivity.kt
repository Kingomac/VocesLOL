package com.kingo.vosesitaslolsito

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun clickevent(v: View) {
        val button = v as Button;
        val intent = Intent(this, VoicesActivity::class.java)
        intent.putExtra("champ", button.text)
        startActivity(intent)
    }
}
