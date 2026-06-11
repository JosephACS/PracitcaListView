package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.Coil
import coil.ImageLoader
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Adapters.VideoAdapter
import com.example.myapplication.Models.Video
import okhttp3.OkHttpClient
import org.json.JSONObject
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

class MainActivity : AppCompatActivity() {

    private val apiUrl = "https://apiws.uteq.edu.ec/h6RPoSoRaah0Y4Bah28eew/functions/information/entity/3"
    private val accessToken = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val imageLoader = ImageLoader.Builder(this)
            .okHttpClient {
                OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier { _, _ -> true }
                    .build()
            }
            .build()
        Coil.setImageLoader(imageLoader)

        val listVideos = findViewById<ListView>(R.id.listVideos)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        listVideos.setOnItemClickListener { parent, view, position, id ->
            val video = parent.getItemAtPosition(position) as Video
            if (video.urlVideo.isNotEmpty()) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(video.urlVideo))
                startActivity(intent)
            }
        }

        progressBar.visibility = View.VISIBLE

        val hurlStack = object : HurlStack() {
            override fun createConnection(url: java.net.URL): java.net.HttpURLConnection {
                val connection = super.createConnection(url) as HttpsURLConnection
                connection.sslSocketFactory = sslContext.socketFactory
                connection.hostnameVerifier = HostnameVerifier { _, _ -> true }
                return connection
            }
        }

        val queue = Volley.newRequestQueue(this, hurlStack)

        val jsonArrayRequest = object : JsonArrayRequest(
            Request.Method.GET, apiUrl, null,
            Response.Listener { response ->
                progressBar.visibility = View.GONE
                try {
                    val lista = mutableListOf<Video>()
                    for (i in 0 until response.length()) {
                        val item: JSONObject = response.getJSONObject(i)
                        lista.add(Video(
                            item.optString("titulo", "Sin título"),
                            item.optString("fechapub", "Sin fecha"),
                            item.optString("portadaVideo", ""),
                            item.optString("urlvideo1", "")
                        ))
                    }
                    listVideos.adapter = VideoAdapter(this, lista)
                } catch (e: Exception) {
                    Toast.makeText(this, "Error procesando datos", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                progressBar.visibility = View.GONE
                Toast.makeText(this, "Error de red: ${error.message}", Toast.LENGTH_LONG).show()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Authorization"] = "Bearer $accessToken"
                return headers
            }
        }

        queue.add(jsonArrayRequest)
    }
}
