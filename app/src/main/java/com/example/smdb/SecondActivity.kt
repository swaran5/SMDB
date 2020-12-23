package com.example.smdb

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smdb.ServiceBuilder.url
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_activity.*
import java.lang.System.load

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        supportActionBar?.hide()

        val bundle: Bundle? = intent.extras
        val title = bundle?.getString("key1")
        val lang = bundle?.getString("key2")
        val date = bundle?.getString("key3")
        val genre = bundle?.getString("key4")
        val backdrop = bundle?.getString("key5")
        val overview = bundle?.getString("key6")

        var secondViewModle : SecondViewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        secondViewModle.loadintro(
            backdrop,
            title,
            lang,
            date,
            genre,
            overview
        )

        collapsing.title = title
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar)
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar)

        secondViewModle.introtitle.observe(this,{
            textView1.text = it

        })
        secondViewModle.introlang.observe(this, {
            textView2.text = it
        })
        secondViewModle.introdate.observe(this, {
            textView3.text = it
        })
        secondViewModle.introgenre.observe(this, {
            textView4.text = it
        })
        secondViewModle.introoverview.observe(this, {
            textView5.text = it
        })
        secondViewModle.introurl.observe(this, {
            Picasso.get().load(it).into(findViewById<ImageView>(R.id.poster))
        })

        share.setOnClickListener {
            val intro =
                "Hey Checkout this Movie.." + "${"\n\n" + textView1.text + "\n\n" + textView3.text + "\n\n" + textView4.text}"
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, intro)
            intent.type = "text/plain"
            startActivity(intent)
        }
    }
}
