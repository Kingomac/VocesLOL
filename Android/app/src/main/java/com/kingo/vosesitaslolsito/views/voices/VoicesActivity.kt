package com.kingo.vosesitaslolsito.views.voices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.kingo.vosesitaslolsito.databinding.ActivityVoicesBinding
import com.kingo.vosesitaslolsito.util.ExprFileReader
import com.kingo.vosesitaslolsito.util.JsonLinksParser
import com.kingo.vosesitaslolsito.util.SoundNamer
import com.kingo.vosesitaslolsito.util.SoundsData
import java.io.InputStream

class VoicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoicesBinding
    private val champ: String by lazy {
        try {
            intent.getStringExtra("champ")!!
        } catch (e: Exception) {
            throw Exception("Champion not found")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoicesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = try {
            applicationContext.resources.getString(applicationContext.resources.getIdentifier(champ.lowercase(), "string", packageName))
        } catch (e: Exception) {
            "Lol no tiene bugs"
        }
        val inputStream = assets.open("skins/${champ.lowercase()}.json")
        val buffered = inputStream.bufferedReader()
        val urls: Array<SoundsData> = JsonLinksParser.parseToArray(buffered.readText())
        val adapter = SoundsViewPagerAdapter(this, urls, champ)
        binding.viewPager.adapter = adapter
        Log.e("URLS:", urls.toString())
        
        TabLayoutMediator(binding.tablayout, binding.viewPager) { tab, position ->
            tab.text = urls[position].key
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = true

    override fun onStart() {
        super.onStart()
        SoundNamer.subExpr = try {
            ExprFileReader.readAll(assets.open("subexpressions/${champ.split("_")[0]}.csv"))
        } catch (e: Exception) {
            mapOf()
        }
    }
}