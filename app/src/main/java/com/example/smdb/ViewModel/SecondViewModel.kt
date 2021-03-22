package com.example.smdb.ViewModel

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.StyleSpan
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.second_activity.*
import java.util.*

class SecondViewModel : ViewModel() {

    var introurl: MutableLiveData<String> = MutableLiveData()
    var introtitle: MutableLiveData<SpannableStringBuilder> = MutableLiveData()
    var introlang: MutableLiveData<SpannableStringBuilder> = MutableLiveData()
    var introdate: MutableLiveData<SpannableStringBuilder> = MutableLiveData()
    var introgenre: MutableLiveData<SpannableStringBuilder> = MutableLiveData()
    var introoverview: MutableLiveData<SpannableStringBuilder> = MutableLiveData()

    val baseimageurl: String = "https://image.tmdb.org/t/p/w500"
    fun loadintro(
        backdrop: String?,
        title: String?,
        lang: String?,
        date: String?,
        genre: String?,
        overview: String?

    ) {
        introurl.postValue(baseimageurl + backdrop)

        val spannabletiltle = SpannableStringBuilder(title)
        spannabletiltle.insert(0, "Title :  ")

        spannabletiltle.setSpan(
            StyleSpan(Typeface.BOLD),
            6, // start
            spannabletiltle.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        introtitle.postValue(spannabletiltle)

        val spannablelang = SpannableStringBuilder(lang)
        spannablelang.insert(0, "Language :  ")

        spannablelang.setSpan(
            StyleSpan(Typeface.BOLD),
            8, // start
            spannablelang.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        introlang.postValue(spannablelang)

        val spannabledate = SpannableStringBuilder(date)
        spannabledate.insert(0, "Release date :  ")

        spannabledate.setSpan(
            StyleSpan(Typeface.BOLD),
            14, // start
            spannabledate.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        introdate.postValue(spannabledate)

        val spannablegenre = SpannableStringBuilder(genre)
        spannablegenre.insert(0, "Genres :  ")

        spannablegenre.setSpan(
            StyleSpan(Typeface.BOLD),
            8, // start
            spannablegenre.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        introgenre.postValue(spannablegenre)

        val spannableoverview = SpannableStringBuilder(overview)
        spannableoverview.insert(0, "Overview :  \n")

        spannableoverview.setSpan(
            StyleSpan(Typeface.BOLD),
            10, // start
            spannableoverview.length, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        introoverview.postValue(spannableoverview)
    }

}