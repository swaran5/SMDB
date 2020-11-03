package com.example.smdb

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.smdb.ServiceBuilder.url
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdapter(val context: Context, val result: List<Result>) :
    RecyclerView.Adapter<MyAdapter.MyHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        return MyHolder(view)

    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val movie = result[position]
        holder.setData(movie,result)
    }

    override fun getItemCount(): Int {
        return result.size
    }

    inner class MyHolder(val v: View) : RecyclerView.ViewHolder(v) {
        var genres =""
        var langs = ""
        var date = ""
        var title = ""
        var url = ""
        var backdrop = ""
        var overview = ""

        init {
            v.setOnClickListener {

                val intent = Intent(context, SecondActivity::class.java)
                intent.action = Intent.ACTION_SEND
                intent.putExtra("key1", title)
                intent.putExtra("key2", langs )
                intent.putExtra("key3", date)
                intent.putExtra("key4", genres)
                intent.putExtra("key5", backdrop)
                intent.putExtra("key6", overview)

                startActivity(context,intent,null)

            }
        }

        fun genres(moviesobj : Result, movieres :List<Result> ) {
            val request = ServiceBuilder.buildService(EndPoints::class.java)
            val call = request.getGenres("8bce9ec9952a3c292c2a37cd539e8464")

            call.enqueue(object : Callback<Genres> {
                override fun onResponse(call: Call<Genres>, response: Response<Genres>) {

                  var  genreresult = response.body()?.genres
                    val genre = moviesobj.genre_ids
                    var genrename = ""


                    for (i in 0..genre.size - 1) {
                        if (i == 0) {
                            val oldgenre = genre[i]

                            if (genreresult != null) {
                                for (j in 0..genreresult.size-1) {

                                    val genreid = genreresult?.get(j)?.id.toString()
                                    if(genreid == oldgenre) {
                                        genrename = genreresult?.get(j)?.name
                                        v.findViewById<TextView>(R.id.genreVal).text = genrename
                                    }

                                }
                            }
                        }
                        else {
                            val oldgenre = genre[i]

                            if (genreresult != null) {
                                for (j in 0..genreresult.size-1) {
                                    val genreid = genreresult?.get(j)?.id.toString()
                                    if(genreid == oldgenre) {
                                        genrename = genrename + genreresult?.get(j)?.name
                                        v.findViewById<TextView>(R.id.genreVal).text = genrename
                        }
                                }
                            }
                        }
                        genres = v.findViewById<TextView>(R.id.genreVal).text.toString()

                        if (i != genre.size-1){
                            genrename = genrename+", "
                        }
                    }
                }

                override fun onFailure(call: Call<Genres>, t: Throwable) {
                    TODO("Not yet implemented")

                }
            })
        }

        fun setData(obj: Result,res :List<Result>) {

            val baseimageurl: String = "https://image.tmdb.org/t/p/w500"
            val imageurl = obj.poster_path
             url = baseimageurl + imageurl
            Picasso.get().load(url).into(v.findViewById<ImageView>(R.id.imageView))

              v.findViewById<TextView>(R.id.titleVal).text = obj.title
              title = v.findViewById<TextView>(R.id.titleVal).text.toString()

             v.findViewById<TextView>(R.id.dateVal).text = obj.release_date
            date = v.findViewById<TextView>(R.id.dateVal).text.toString()


            val lang = obj.original_language

            when (lang) {
                "en" -> v.findViewById<TextView>(R.id.languageVal).text = "English"
                "ja" -> v.findViewById<TextView>(R.id.languageVal).text = "Japanise"
                "ko" -> v.findViewById<TextView>(R.id.languageVal).text = "Korean"
                "es" -> v.findViewById<TextView>(R.id.languageVal).text = "Spanish"
                "it" -> v.findViewById<TextView>(R.id.languageVal).text = "Italian"
                "hi" -> v.findViewById<TextView>(R.id.languageVal).text = "Hindi"
                else -> v.findViewById<TextView>(R.id.languageVal).text = lang
            }
            langs = v.findViewById<TextView>(R.id.languageVal).text.toString()
            genres(obj , res)

            backdrop = obj.backdrop_path

            overview = obj.overview

        }

    }
}
