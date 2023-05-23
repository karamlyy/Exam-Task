package com.example.examtask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(
    functions: MovieAdapter.Functions
): RecyclerView.Adapter<MovieAdapter.Holder>() {

    private var movies: List<Movie> = listOf()

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var original_title = itemView.findViewById<TextView>(R.id.titleMovie)
        var vote_average = itemView.findViewById<TextView>(R.id.voteMovie)
        var poster_path = itemView.findViewById<ImageView>(R.id.imageView)
        var overviewMovie = itemView.findViewById<TextView>(R.id.overviewMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_movie,parent,false)
        )
    }

    override fun getItemCount(): Int = movies.count()

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = movies[position]
        holder.original_title.text = movie.title
        holder.vote_average.text = movie.vote_average.toString()
        holder.overviewMovie.text = movie.overview.toString()
        if (!movie.poster_path.isNullOrEmpty()) {
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .resize(200,200)
                .into(holder.poster_path)

        } else {
            Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTN0neuoQP1nuyt-TtkqkLsaZQQHGHgIz1TDkiShd10UA&s")
                .into(holder.poster_path)
        }
    }

    fun set(movies: List<Movie>) {
        this.movies = movies
        this.notifyDataSetChanged()
    }

    interface Functions {

    }
}