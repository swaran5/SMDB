package com.example.smdb

import android.content.Context
import android.icu.number.NumberFormatter.with
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.RemoteConference
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val context = this
    val request = ServiceBuilder.buildService(EndPoints::class.java)
    val key = "8bce9ec9952a3c292c2a37cd539e8464"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val category = arrayOf("Top Rated", "Now Playing", "Up Coming", "Popular")

        select_category.adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, category)

        select_category.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (category[position]) {

                    "Popular" -> PopularMovies()
                    "Now Playing" -> NowPlaying()
                    "Top Rated" -> TopRated()
                    "Up Coming" -> UpComing()

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun PopularMovies() {

        val call = request.getMovies(key)

        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {

                recyclerView.layoutManager = LinearLayoutManager(context)

                val adapter = response.body()?.results?.let { MyAdapter(context, it) }
                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("Home Screen", t.toString())
            }

        })
    }

    fun NowPlaying() {

        val call = request.getNowPlaying(key)

        call.enqueue(object : Callback<NowPlaying> {
            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {

                recyclerView.layoutManager = LinearLayoutManager(context)

                val adapter = response.body()?.results?.let { MyAdapter(context, it) }
                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("Home Screen", t.toString())
            }
        })
    }

    fun TopRated() {

        val call = request.getTopRated(key)

        call.enqueue(object : Callback<TopRated> {
            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {

                recyclerView.layoutManager = LinearLayoutManager(context)

                val adapter = response.body()?.results?.let { MyAdapter(context, it) }
                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("Home Screen", t.toString())

            }

        })
    }

    fun UpComing() {

        val call = request.getUpComing(key)

        call.enqueue(object : Callback<UpComing> {

            override fun onResponse(call: Call<UpComing>, response: Response<UpComing>) {

                recyclerView.layoutManager = LinearLayoutManager(context)

                val adapter = response.body()?.results?.let { MyAdapter(context, it) }
                recyclerView.adapter = adapter

            }

            override fun onFailure(call: Call<UpComing>, t: Throwable) {

                Log.d("Home Screen", t.toString())
            }

        })
    }
}