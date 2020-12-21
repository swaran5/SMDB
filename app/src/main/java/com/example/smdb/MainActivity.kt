package com.example.smdb

import android.content.Context
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
    lateinit var name: String

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

                    "Popular" -> name = "popular"
                    "Now Playing" -> name = "now_playing"
                    "Top Rated" -> name = "top_rated"
                    "Up Coming" -> name = "upcoming"

                }
                val call = request.getMovies(name, key)

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

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}