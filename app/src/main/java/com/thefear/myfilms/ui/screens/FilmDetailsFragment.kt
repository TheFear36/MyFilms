package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.thefear.myfilms.R
import com.thefear.myfilms.databinding.FragmentFilmDetailsBinding
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.ui.viewmodels.DetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailsFragment : Fragment() {

    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getParcelable<Film>(FILM_EXTRA)?.let {
            with(binding) {
                val film = it
                titleFilm.text = film.title
                filmDetailsTextView.text = film.details
                Glide.with(coverImageView.context)
                    .load(film.cover)
                    .placeholder(R.drawable.ic_default_film)
                    .error(R.drawable.ic_default_film)
                    .into(coverImageView)
                viewModel.saveEntity(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val FILM_EXTRA = "FILM"

        fun newInstance(bundle: Bundle): FilmDetailsFragment {
            val fragment = FilmDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}