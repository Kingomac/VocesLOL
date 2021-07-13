package com.kingo.vosesitaslolsito

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMultiplePermissions.launch(arrayOf(Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE))
    }


    fun clickevent(v: View) {
        val button = v as Button;
        val intent = Intent(this, VoicesActivity::class.java)
        intent.putExtra("champ", button.text)
        startActivity(intent)
        testsbtn.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Tu viejaaaaaaaaaaaaaaaa")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_WRITE_EXTERNAL_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission Write", "GRANTED")
                } else {
                    AlertDialog.Builder(this).setTitle("Permisos").setMessage("ASÍ NO SE PUEDE").setNeutralButton("💩") { v, i -> v.dismiss() }
                        .show()
                }
                return
            }
            PERMISSION_READ_EXTERNAL_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission Read", "GRANTED")
                } else {
                    AlertDialog.Builder(this).setTitle("Permisos").setMessage("VA A DARME PERMISO TU VIEJAAAAAAA").setNeutralButton("💩") { v, i -> v.dismiss() }
                        .show()
                }
                return
            }
        }
    }
}
