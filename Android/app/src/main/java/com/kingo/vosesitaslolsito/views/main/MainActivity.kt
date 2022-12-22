package com.kingo.vosesitaslolsito.views.main

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.kingo.vosesitaslolsito.util.Champ
import com.kingo.vosesitaslolsito.R
import com.kingo.vosesitaslolsito.views.voices.VoicesActivity
import com.kingo.vosesitaslolsito.databinding.ActivityMainBinding
import com.kingo.vosesitaslolsito.util.ExprFileReader
import com.kingo.vosesitaslolsito.util.SoundNamer

class MainActivity : AppCompatActivity() {

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){
            permissions ->
        permissions.entries.forEach {
            Log.e("DEBUG", "${it.key} = ${it.value}")
        }
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        requestMultiplePermissions.launch(arrayOf(
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
        binding.recyclerview.layoutManager = GridLayoutManager(this, 3)
        val champs: Array<String>;
        assets.open("champ_list.txt").apply {
            champs = this.readBytes().toString(Charsets.UTF_8).lines().toTypedArray()
        }.close()
        adapter = MainRecyclerViewAdapter(champs, applicationContext)
        adapter.setOnClickListener {
            val c = adapter.filteredChamps[binding.recyclerview.getChildAdapterPosition(it)]
            val intent = Intent(this, VoicesActivity::class.java)
            intent.putExtra("champ", c.lowercase())
            startActivity(intent)
        }
        binding.recyclerview.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val item = menu.findItem(R.id.search_action)
        val searchView = item?.actionView as androidx.appcompat.widget.SearchView
        searchView.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onStart() {
        super.onStart()
        SoundNamer.generalExpr = ExprFileReader.readAll(assets.open("generalExpr.csv"))
    }
    
}