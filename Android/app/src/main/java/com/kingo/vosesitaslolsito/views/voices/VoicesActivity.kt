package com.kingo.vosesitaslolsito.views.voices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayoutMediator
import com.kingo.vosesitaslolsito.databinding.ActivityVoicesBinding
import com.kingo.vosesitaslolsito.util.JsonLinksParser
import com.kingo.vosesitaslolsito.util.SoundsData
import java.io.InputStream

class VoicesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVoicesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoicesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val champ: String = try {
            intent.getStringExtra("champ").toString()
        } catch (e: Exception) {
            throw Exception("Cagaaaaaaaaaaste")
        }
        title = try {
            applicationContext.resources.getString(applicationContext.resources.getIdentifier(champ.lowercase(), "string", packageName))
        } catch (e: Exception) {
            "Lol no tiene bugs"
        }
        val inputStream: InputStream = resources.openRawResource(
            resources.getIdentifier(
                champ.lowercase(),
                "raw",
                packageName
            )
        )
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
}