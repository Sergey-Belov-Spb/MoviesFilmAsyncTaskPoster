package com.example.moviesfilmasynctaskposter

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

            //val job: Job = GlobalScope.launch(Dispatchers.IO) {
                //val url = buildUrl()

                //download(url) //suspend function
                //downloadPdf(this@MainActivity,"https://image.tmdb.org/t/p/w185/uGhQ2ZGBpzCj6wC5jUrybsZuPTI.jpg","Loading")
            downloadFile("https://image.tmdb.org/t/p/w185/uGhQ2ZGBpzCj6wC5jUrybsZuPTI.jpg");
/*??
            var count: Int=0
            try {
                val url = URL("https://image.tmdb.org/t/p/w185/uGhQ2ZGBpzCj6wC5jUrybsZuPTI.jpg")
                val conection: URLConnection = url.openConnection()
                conection.connect()
                // this will be useful so that you can show a tipical 0-100%
// progress bar
                val lenghtOfFile: Int = conection.getContentLength()
                // download the file
                val input: InputStream = BufferedInputStream(
                    url.openStream(),
                    8192
                )
                // Output stream
                val output: OutputStream = FileOutputStream(
                    Environment
                        .getExternalStorageDirectory().toString()
                        .toString() + "/2011.kml"
                )
                val data = ByteArray(1024)
                var total: Long = 0
                while (input.read(data).also({ count = it }) !== -1) {
                    total += count
                    // publishing the progress....
// After this onProgressUpdate will be called
//                    publishProgress("" + (total * 100 / lenghtOfFile).toInt())
                    // writing data to file
                    output.write(data, 0, count)
                }
                // flushing output
                output.flush()
                // closing streams
                output.close()
                input.close()
            } catch (e: Exception) {
                Log.d(TAG,"Error: "+ e.message)
            }
*/
            //return null
                Log.d(TAG,"GlobalScope.launch")
            //}
            Toast.makeText(this,"Download error: end", Toast.LENGTH_LONG).show();
        }
    }
    
    private fun downloadFile(url: String ) {
        var url =  URL(url)
        val urlConnection =  url.openConnection() as HttpURLConnection
        try {
            urlConnection.setRequestMethod("GET")
            urlConnection.setDoOutput(true)
            urlConnection.connect()

        } catch (e: IOException) {
            e.printStackTrace();
            //m_error = e;
        }
        /*final ProgressDialog progressDialog = new ProgressDialog(this);

        new AsyncTask<String, Integer, File>() {
            private Exception m_error = null;

            @Override
            protected void onPreExecute() {
                progressDialog.setMessage("Downloading ...");
                progressDialog.setCancelable(false);
                progressDialog.setMax(100);
                progressDialog
                    .setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                progressDialog.show();
            }

            @Override
            protected File doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection;
            InputStream inputStream;
            int totalSize;
            int downloadedSize;
            byte[] buffer;
            int bufferLength;

            File file = null;
            FileOutputStream fos = null;

            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                file = File.createTempFile("Mustachify", "download");
                fos = new FileOutputStream(file);
                inputStream = urlConnection.getInputStream();

                totalSize = urlConnection.getContentLength();
                downloadedSize = 0;

                buffer = new byte[1024];
                bufferLength = 0;

                // читаем со входа и пишем в выход,
                // с каждой итерацией публикуем прогресс
                while ((bufferLength = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, bufferLength);
                    downloadedSize += bufferLength;
                    publishProgress(downloadedSize, totalSize);
                }

                fos.close();
                inputStream.close();

                return file;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                m_error = e;
            } catch (IOException e) {
                e.printStackTrace();
                m_error = e;
            }

            return null;*/
        }


    /*fun downloadPdf(baseActivity: Context, url: String?, title: String?): Long {
        val direct = File(Environment.getExternalStorageDirectory().toString() + "/dir")

        if (!direct.exists()) {
            direct.mkdirs()
        }
        val extension = url?.substring(url.lastIndexOf("."))
        val downloadReference: Long
        var  dm: DownloadManager
        dm= baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
        request.setDestinationInExternalPublicDir(
            "/dir",
            "jpg" + System.currentTimeMillis() + extension
        )
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(title)
        Toast.makeText(baseActivity, "start Downloading..", Toast.LENGTH_SHORT).show()

        downloadReference = dm?.enqueue(request) ?: 0

        return downloadReference
    }*/

  /*  protected fun doInBackground(vararg params: Void?): File? { //File sdCardRoot = Environment.getExternalStorageDirectory()+"/Music";
        val file =
            File(Environment.getExternalStorageDirectory().toString() + "/" + FileName)
        if (!file.exists()) {
            file.mkdir()
        }
        val filename = "YOUR LOCAL STORAGE FILE NAME TITLE"
        yourDir = File(file, "$filename.mp3")
        if (yourDir.exists()) {
            return yourDir
        }
        val url = "https://image.tmdb.org/t/p/w185/uGhQ2ZGBpzCj6wC5jUrybsZuPTI.jpg"
        var u: URL? = null
        try {
            Log.d(TAG,"Request Url$url")
            u = URL(url)
            val conn: URLConnection = u.openConnection()
            val contentLength: Int = conn.getContentLength()
            val stream = DataInputStream(u.openStream())
            val buffer = ByteArray(contentLength)
            stream.readFully(buffer)
            stream.close()
            val fos = DataOutputStream(FileOutputStream(yourDir))
            fos.write(buffer)
            fos.flush()
            fos.close()
            Log.d(TAG,"Download Complete in On Background")
        } catch (e: MalformedURLException) {
            sucess = false
            e.printStackTrace()
        } catch (e: IOException) {
            sucess = false
            e.printStackTrace()
        } catch (e: Exception) {
            sucess = false
            e.printStackTrace()
            DebugLog.e("Error ::" + e.message)
        }
        return yourDir
    }*/



}
