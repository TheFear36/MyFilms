package com.thefear.myfilms.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thefear.myfilms.R
import com.thefear.myfilms.databinding.ItemFilmBinding
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.ui.screens.FilmListFragment

class FilmsAdapter(private val itemClickListener: FilmListFragment.OnItemViewClickListener) :
    RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    var films: List<Film> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFilmBinding.inflate(inflater, parent, false)
        return FilmsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val film = films[position]
        with(holder.binding) {
            titleFilm.text = film.title
            filmYear.text = film.year
            filmRate.text = film.rate
            Glide.with(coverImageView.context)
                .load(film.cover)
                .placeholder(R.drawable.ic_default_film)
                .error(R.drawable.ic_default_film)
                .into(coverImageView)
            root.setOnClickListener { itemClickListener.onItemViewClick(film) }
        }
    }

    override fun getItemCount(): Int = films.size

    class FilmsViewHolder(
        val binding: ItemFilmBinding
    ) : RecyclerView.ViewHolder(binding.root)

}