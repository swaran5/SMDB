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
import com.example.smdb.ServiceBuilder.url
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.second_activity.*
import java.lang.System.load

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        supportActionBar?.hide()

        val baseimageurl: String = "https://image.tmdb.org/t/p/w500"

        val bundle: Bundle? = intent.extras
        val title = bundle?.getString("key1")
        val lang = bundle?.getString("key2")
        val date = bundle?.getString("key3")
        val genre = bundle?.getString("key4")
        val backdrop = bundle?.getString("key5")
        val overview   = bundle?.getString("key6")

        val url = baseimageurl + backdrop

        collapsing.title = title
        collapsing.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        val spannabletiltle = SpannableStringBuilder(title)
        spannabletiltle.insert(0,"Title :  ")

        spannabletiltle.setSpan(
            StyleSpan(BOLD),
            6, // start
            spannabletiltle.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val spannablelang = SpannableStringBuilder(lang)
        spannablelang.insert(0,"Language :  ")

        spannablelang.setSpan(
            StyleSpan(BOLD),
            8, // start
            spannablelang.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val spannabledate = SpannableStringBuilder(date)
        spannabledate.insert(0,"Release date :  ")

        spannabledate.setSpan(
            StyleSpan(BOLD),
            14, // start
            spannabledate.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val spannablegenre = SpannableStringBuilder(genre)
        spannablegenre.insert(0,"Genres :  ")

        spannablegenre.setSpan(
            StyleSpan(BOLD),
            8, // start
            spannablegenre.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        val spannableoverview = SpannableStringBuilder(overview)
        spannableoverview.insert(0,"Overview :  \n")

        spannableoverview.setSpan(
            StyleSpan(BOLD),
            10, // start
            spannableoverview.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        textView1.text = spannabletiltle
        textView2.text = spannablelang
        textView3.text = spannabledate
        textView4.text = spannablegenre
        textView5.text = spannableoverview

        Picasso.get().load(url).into(findViewById<ImageView>(R.id.poster))

        share.setOnClickListener {
            val intro = "Hey Checkout this Movie.." + "${"\n\n"+ spannabletiltle + "\n\n" + spannabledate +"\n\n" + spannablegenre}"
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, intro)
            intent.type = "text/plain"
            startActivity(intent)
        }
    }
}
