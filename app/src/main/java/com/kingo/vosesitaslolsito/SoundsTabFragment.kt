package com.kingo.vosesitaslolsito

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
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

    enum class DownloadCompletedAction {
        PLAY_FILE,
        SHARE_FILE
    }

    private var SOUNDS_PATH: Uri? = null
    private var player: MediaPlayer? = null
    private var downloadId: Long = -1
    private var fileUri: Uri? = null
    private val onDownloadComplete = CustomBroadcastReceiver()
    private var onDownloadCompleteAction = DownloadCompletedAction.PLAY_FILE
    private var buttonToPaint: Button? = null
    private var shareIntent: Intent? = null

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
        //SOUNDS_PATH = Uri.fromFile(context?.getExternalFilesDir(null))
        //SOUNDS_PATH = Uri.parse("file://" + MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.path)
        SOUNDS_PATH = Uri.fromFile(activity?.externalMediaDirs?.get(0))
        arguments?.takeIf { it.containsKey(SoundsViewPagerAdapter.ARG_LINKS) }?.apply {
                    val links = getStringArray(SoundsViewPagerAdapter.ARG_LINKS)
                    val champ = getString(SoundsViewPagerAdapter.ARG_CHAMP)
                    links?.forEach { url ->
                        val btn = Button(context)
                        btn.text = URLDecoder.decode(getFileNameWithoutExtension(url), java.nio.charset.StandardCharsets.UTF_8.toString())
                        val file = File(SOUNDS_PATH?.path, "/$champ/${getFileNameWithExtension(url)}")
                        if(file.exists()) paintButtonAsDownloaded(btn)
                        btn.setOnClickListener { v ->
                            if(file.exists()) {
                                Log.i("SHARE", "File exists")
                                player = MediaPlayer.create(context, Uri.fromFile(file))
                                player?.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build())
                                player?.start()
                            } else {
                                Log.i("SHARE", "File does not exist")
                                //downloadbegin(Uri.parse(url),"/$champ/${getFileNameWithExtension(url)}")
                                onDownloadCompleteAction = DownloadCompletedAction.PLAY_FILE
                                buttonToPaint = btn
                                downloadbegin(Uri.parse(url), file)
                            }
                        }
                        btn.setOnLongClickListener {

                            val uri = context?.let { it1 -> FileProvider.getUriForFile(it1, context?.applicationContext?.packageName + ".provider", file) }

                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "audio/mp3"
                                putExtra(Intent.EXTRA_STREAM, uri)
                                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                            }
                            shareIntent = Intent.createChooser(sendIntent, null)

                            if(file.exists()) {
                                startActivity(shareIntent)
                            } else {
                                onDownloadCompleteAction = DownloadCompletedAction.SHARE_FILE
                                buttonToPaint = btn
                                downloadbegin(Uri.parse(url), file)
                            }

                            true


                        }
                        linearlayout.addView(btn)
                    }
        }
    }

    private fun paintButtonAsDownloaded(btn: Button) {
        btn.backgroundTintList = context?.resources?.getColorStateList(R.color.purple_500, context?.theme)
        context?.resources?.getColor(R.color.white, context?.theme)?.let {
            btn.setTextColor(
                it
            )
        }
    }

    private fun getFileNameWithExtension(path: String) = path.split('/').last()
    private fun getFileNameWithoutExtension(path: String) = getFileNameWithExtension(path).split('.').first()

    private fun downloadbegin(source: Uri, fileChild: String) {
        val req =
            DownloadManager.Request(source)
                .setTitle(getFileNameWithoutExtension(source.toString()))
                .setDescription(activity?.getString(R.string.app_name))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                //.setDestinationUri(Uri.parse(SOUNDS_PATH?.path + fileChild))
                .setDestinationInExternalPublicDir(SOUNDS_PATH?.path, fileChild)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        val manager = activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(req)
        fileUri = Uri.parse(SOUNDS_PATH?.path + fileChild)
    }

    private fun downloadbegin(source: Uri, file: File) {
        val req =
            DownloadManager.Request(source)
                .setTitle(getFileNameWithoutExtension(source.toString()))
                .setDescription(activity?.getString(R.string.app_name))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setDestinationUri(Uri.fromFile(file))
                //.setDestinationInExternalPublicDir(SOUNDS_PATH?.path, fileChild)
                .setRequiresCharging(false)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(true)
        val manager = activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(req)
        fileUri = Uri.fromFile(file)
    }

    inner class CustomBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            buttonToPaint?.let { paintButtonAsDownloaded(it) }
            if (downloadId == id) {
                when(onDownloadCompleteAction) {
                    DownloadCompletedAction.PLAY_FILE -> {
                        player = MediaPlayer.create(context, fileUri)
                        player?.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_MEDIA).build())
                        player?.start()
                    }
                    DownloadCompletedAction.SHARE_FILE -> {
                        startActivity(shareIntent)
                    }
                }

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