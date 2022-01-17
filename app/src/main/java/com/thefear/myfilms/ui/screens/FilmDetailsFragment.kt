package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.thefear.myfilms.R
import com.thefear.myfilms.databinding.FragmentFilmDetailsBinding
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.ui.FilmsDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmDetailsFragment : Fragment() {

    private val viewModel: FilmsDetailsViewModel by viewModel()
    private var _binding: FragmentFilmDetailsBinding? = null
    private val binding get() = _binding!!

    private val selectedFilm: Film
        get() {
            TODO()
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFilmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.update(selectedFilm)
        setData(selectedFilm)
    }


    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessOne -> {
                val filmData = appState.filmData
                contentContainer.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                setData(filmData)
            }
            is AppState.Error -> {
                Snackbar.make(detailsContainer, R.string.errorLoading, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.reloadError) { viewModel.update(selectedFilm) }
                    .show()
            }
            is AppState.Loading -> {
                contentContainer.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            else -> {
                Snackbar.make(detailsContainer, R.string.errorLoading, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.reloadError) { viewModel.update(selectedFilm) }
                    .show()
            }
        }
    }

    private fun setData(filmsData: Film) = with(binding) {
        titleFilm.text = filmsData.title
        filmDetailsTextView.text = selectedFilm.details
        Glide.with(coverImageView.context)
            .load(selectedFilm.cover)
            .placeholder(R.drawable.ic_default_film)
            .error(R.drawable.ic_default_film)
            .into(coverImageView)
    }

}