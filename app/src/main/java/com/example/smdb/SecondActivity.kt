package com.example.smdb

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.smdb.ServiceBuilder.url
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_activity.*
import java.lang.System.load

class SecondActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        supportActionBar?.hide()

        val baseimageurl: String = "https://image.tmdb.org/t/p/w500"

        val bundle : Bundle? = intent.extras
        val title = bundle?.getString("key1")
        val lang = bundle?.getString("key2")
        val date = bundle?.getString("key3")
        val genre = bundle?.getString("key4")
        val backdrop = bundle?.getString("key5")
        val overview = bundle?.getString("key6")

        val url = baseimageurl+backdrop

        collapsing.title = title

        textView.text = title +"\n\n"+ lang +"\n\n"+ date +"\n\n"+ genre+"\n\n"+ overview

       Picasso.get().load(url).into(findViewById<ImageView>(R.id.poster))

    }
}