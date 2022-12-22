package com.kingo.vosesitaslolsito.views.voices

import android.app.AlertDialog
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.kingo.vosesitaslolsito.R
import com.kingo.vosesitaslolsito.databinding.FragmentSoundsTabBinding
import com.kingo.vosesitaslolsito.util.SoundNamer
import java.io.File
import java.net.URLDecoder

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
    private var isDownloading = false
    private lateinit var binding: FragmentSoundsTabBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.registerReceiver(
            onDownloadComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSoundsTabBinding.inflate(layoutInflater)
        return binding.root
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
                /*btn.text = URLDecoder.decode(
                    getFileNameWithoutExtension(url),
                    java.nio.charset.StandardCharsets.UTF_8.toString()
                )*/
                val defName = URLDecoder.decode(
                    getFileNameWithoutExtension(url),
                    Charsets.UTF_8.toString()
                )
                btn.text = SoundNamer.rename(defName)
                Log.i("SoundNamer", "Old name: $defName | New Name: ${btn.text}")
                val file = File(SOUNDS_PATH?.path, "/$champ/${getFileNameWithExtension(url)}")
                if (file.exists()) paintButtonAsDownloaded(btn)
                btn.setOnClickListener {
                    if (file.exists()) {
                        Log.i("PLAY", "File exists")
                        play(Uri.fromFile(file))

                    } else {
                        Log.i("PLAY", "File does not exist")
                        if (canDownload()) {
                            onDownloadCompleteAction =
                                DownloadCompletedAction.PLAY_FILE
                            buttonToPaint = btn
                            downloadbegin(Uri.parse(url), file)
                        }
                    }
                }
                btn.setOnLongClickListener {
                    val uri = context?.let { it1 ->
                        FileProvider.getUriForFile(
                            it1,
                            context?.applicationContext?.packageName + ".provider",
                            file
                        )
                    }
                    val sendIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "audio/mp3"
                        putExtra(Intent.EXTRA_STREAM, uri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    shareIntent = Intent.createChooser(sendIntent, null)
                    if (file.exists()) {
                        startActivity(shareIntent)
                    } else if (canDownload()) {
                        onDownloadCompleteAction =
                            DownloadCompletedAction.SHARE_FILE
                        buttonToPaint = btn
                        downloadbegin(Uri.parse(url), file)
                    }
                    true
                }
                binding.linearlayout.addView(btn)
            }
        }
    }

    private fun canDownload(): Boolean {
        if (isDownloading) {
            AlertDialog.Builder(context).setTitle("Ya estás descargando algo")
                .setMessage("Deja esa Q Penta Yi, pero recuerda, la táctica maestra es no rendirse")
                .setNeutralButton("Me cago en tu vieja") { v, _ -> v.dismiss() }
                .create().show()
            return false
        }
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.getNetworkCapabilities(cm.activeNetwork)
        var netAvailable = false
        netInfo?.let {
            netAvailable = when {
                it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }
        if (!netAvailable) {
            AlertDialog.Builder(context).setTitle("Moroooooso")
                .setMessage("Paga el internet moroooooooooosooooooo")
                .setNeutralButton("Naaah 1000 de ping va finísimo") { v, _ -> v.dismiss() }
                .create().show()
            return false
        }
        return true
    }

    private fun play(uri: Uri) {
        player?.let {
            it.stop()
            it.release()
        }
        player = MediaPlayer.create(context, uri)
        player?.setAudioAttributes(
            AudioAttributes.Builder().setContentType(
                AudioAttributes.CONTENT_TYPE_MUSIC
            ).setUsage(AudioAttributes.USAGE_MEDIA).build()
        )
        player?.setOnCompletionListener {
            it.release()
            player = null
        }
        player?.start()
    }

    private fun paintButtonAsDownloaded(btn: Button) {
        btn.backgroundTintList =
            context?.resources?.getColorStateList(R.color.purple_500, context?.theme)
        context?.resources?.getColor(R.color.white, context?.theme)?.let {
            btn.setTextColor(
                it
            )
        }
    }

    private fun getFileNameWithExtension(path: String) = path.split('/').last()
    private fun getFileNameWithoutExtension(path: String) =
        getFileNameWithExtension(path).split('.').first()

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
        val manager =
            activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(req)
        fileUri = Uri.parse(SOUNDS_PATH?.path + fileChild)
        isDownloading = true
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
        val manager =
            activity?.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager
        downloadId = manager.enqueue(req)
        fileUri = Uri.fromFile(file)
        isDownloading = true
    }

    inner class CustomBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            isDownloading = false
            val id = intent?.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            buttonToPaint?.let { paintButtonAsDownloaded(it) }
            if (downloadId == id) {
                when (onDownloadCompleteAction) {
                    DownloadCompletedAction.PLAY_FILE -> {
                        fileUri?.let {
                            play(it)
                        }
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
        @JvmStatic
        fun newInstance() = SoundsTabFragment()
    }
}