package com.kingo.vosesitaslolsito

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_voices.*
import java.io.InputStream

class VoicesActivity() : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE: Int = 1000

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voices)

        //viewPager.isUserInputEnabled = false
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var champ: String = try {
            intent.getStringExtra("champ").toString()
        } catch (e: Exception) {
            throw Exception("Cagaaaaaaaaaaste")
        }
        title = champ;
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
        viewPager.adapter = adapter
        Log.e("URLS:", urls.toString())

        var i = 0
        TabLayoutMediator(tablayout, viewPager) { tab, position ->
            tab.text = urls[i].key
            i++
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean = true

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Reload the app please", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "No me diste permisooooooo", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}