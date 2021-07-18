package com.kingo.vosesitaslolsito

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.kingo.vosesitaslolsito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_WRITE_EXTERNAL_CODE = 1
        private const val PERMISSION_READ_EXTERNAL_CODE = 2
    }

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions ->
        permissions.entries.forEach {
            Log.e("DEBUG", "${it.key} = ${it.value}")
        }
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        requestMultiplePermissions.launch(arrayOf(
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        binding.recyclerview.layoutManager = GridLayoutManager(this, 3)
        val recyclerAdapter = MainRecyclerViewAdapter(Champ.values(), applicationContext)
        recyclerAdapter.setOnClickListener {
            val c = Champ.values()[binding.recyclerview.getChildAdapterPosition(it)]
            val intent = Intent(this, VoicesActivity::class.java)
            intent.putExtra("champ", c.name.lowercase())
            startActivity(intent)
        }
        binding.recyclerview.adapter = recyclerAdapter
    }
}