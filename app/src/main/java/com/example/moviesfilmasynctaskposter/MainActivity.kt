package com.example.moviesfilmasynctaskposter

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection


class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActiviry"
        const val FileName = "TestDownload.jpg"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLoadPoster.setOnClickListener {
            Log.d(TAG,"btnLoadPoster")


            downloadJpg(this, "https://image.tmdb.org/t/p/w185/uGhQ2ZGBpzCj6wC5jUrybsZuPTI.jpg", "Load file")

            Log.d(TAG,"GlobalScope.launch")
            Toast.makeText(this,"Download error: end", Toast.LENGTH_LONG).show();
        }

    }



    fun downloadJpg(baseActivity: Context, url: String?, title: String?): Long {

        val downloadReference: Long
        var  dm: DownloadManager
        dm= baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, uri.getLastPathSegment())
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(title)

        Toast.makeText(baseActivity, "start Downloading..", Toast.LENGTH_SHORT).show()
        downloadReference = dm?.enqueue(request) ?: 0
        return downloadReference
    }




}
