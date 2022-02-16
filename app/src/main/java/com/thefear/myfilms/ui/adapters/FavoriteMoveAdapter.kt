package com.thefear.myfilms.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thefear.myfilms.R
import com.thefear.myfilms.databinding.ItemFavoriteMoveBinding
import com.thefear.myfilms.model.entities.Film

class FavoriteMoveAdapter:
    RecyclerView.Adapter<FavoriteMoveAdapter.FavoriteMoveViewHolder>() {

    var films: List<Film> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteMoveBinding.inflate(inflater, parent, false)
        return FavoriteMoveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMoveViewHolder, position: Int) {
        val film = films[position]
        with(holder.binding) {
            favoriteTitle.text = film.title
            favoriteYear.text = film.year
            favoriteRate.text = film.rate
            Glide.with(favoritePoster.context)
                .load(film.cover)
                .placeholder(R.drawable.ic_default_film)
                .error(R.drawable.ic_default_film)
                .into(favoritePoster)
        }
    }

    override fun getItemCount(): Int = films.size

    class FavoriteMoveViewHolder(
        val binding: ItemFavoriteMoveBinding
        ): RecyclerView.ViewHolder(binding.root)

}