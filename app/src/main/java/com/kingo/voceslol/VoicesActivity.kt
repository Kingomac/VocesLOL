package com.kingo.voceslol

import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_voices.*
import java.io.InputStream

class VoicesActivity() : AppCompatActivity() {

    private val STORAGE_PERMISSION_CODE: Int = 1000

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voices)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var champ: String = try {
            intent.getStringExtra("champ").toString()
        } catch (e: Exception) {
            throw Exception("Cagaaaaaaaaaaste")
        }
        Toast.makeText(applicationContext, "Champ: $champ", Toast.LENGTH_LONG).show()
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
        val adapter = SoundsViewPagerAdapter(this, urls)
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

    private fun downloadSound(url: String, context: Context) {
        val request = DownloadManager.Request(Uri.parse(url))
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
        request.setTitle("Download")
        request.setDescription("The file is downloading...")

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        val filename = "tengomiedo.mp3"
        request.setDestinationInExternalFilesDir(
            this,
            Environment.DIRECTORY_DOWNLOADS,
            "tengomiedo.mp3"
        )

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(request)

    }

    private fun playyy(uri: Uri) {
        val player = MediaPlayer.create(this, uri)
        player.start()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}