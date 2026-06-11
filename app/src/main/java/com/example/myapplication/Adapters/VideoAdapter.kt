package com.example.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.myapplication.Models.Video
import com.example.myapplication.R

class VideoAdapter(context: Context, videos: List<Video>) :
    ArrayAdapter<Video>(context, 0, videos) {

    private val imageBaseUrl = "https://uteq.edu.ec/assets/images/videos/res-sem/"

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
        }

        val video = getItem(position)

        val txtTitulo = itemView!!.findViewById<TextView>(R.id.txtTitulo)
        val txtFecha = itemView.findViewById<TextView>(R.id.txtFecha)
        val txtLink = itemView.findViewById<TextView>(R.id.txtLink)
        val imgPortada = itemView.findViewById<ImageView>(R.id.imgPortada)

        txtTitulo.text = video?.titulo
        txtFecha.text = "Publicado el: ${video?.fecha_publicacion}"
        txtLink.text = video?.urlVideo

        val imageUrl = imageBaseUrl + video?.portadaVideo
        imgPortada.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_launcher_background)
        }

        return itemView
    }
}
