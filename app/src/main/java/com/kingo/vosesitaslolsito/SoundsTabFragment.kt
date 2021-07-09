package com.kingo.vosesitaslolsito

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_sounds_tab.*
import java.io.File
import java.net.URLDecoder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SoundsTabFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SoundsTabFragment : Fragment() {

    private var player: MediaPlayer? = null
    private var downloadId: Long = -1
    private var fileUri: Uri? = null
    private val onDownloadComplete = CustomBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sounds_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(SoundsViewPagerAdapter.ARG_LINKS) }?.apply {
                    val links = getStringArray(SoundsViewPagerAdapter.ARG_LINKS)
                    val champ = getString(SoundsViewPagerAdapter.ARG_CHAMP)
                    links?.forEach { url ->
                        val btn = Button(context)
                        btn.text = URLDecoder.decode(getFileNameWithoutExtension(url), java.nio.charset.StandardCharsets.UTF_8.toString())
                        btn.setOnClickListener { v ->
                            val file = File(activity?.getExternalFilesDir(null), "/$champ/${getFileNameWithExtension(url)}")
                            if(file.exists()) {
                                player = MediaPlayer.create(activity?.applicationContext, Uri.fromFile(file))
                                player?.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build())
                                player?.start()
                            } else {
                                downloadbegin(Uri.parse(url),file)
                            }
                        }
                        linearlayout.addView(btn)
                    }
        }
    }

    private fun getFileNameWithExtension(path: String) = path.split('/').last()
    private fun getFileNameWithoutExtension(path: String) = getFileNameWithExtension(path).split('.').first()

    private fun downloadbegin(source: Uri, destiny: File) {
        val req =
            DownloadManager.Request(source)
                .setTitle(getFileNameWithoutExtension(source.toString()))
                .setDescription(activity?.getString(R.string.app_name))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(destiny))
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        val manager = activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(req)
        fileUri = Uri.fromFile(destiny)
    }

    inner class CustomBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (downloadId == id) {
                player = MediaPlayer.create(context, fileUri)
                player?.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build())
                player?.start()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.unregisterReceiver(onDownloadComplete)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SoundsTabFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = SoundsTabFragment()
    }
}