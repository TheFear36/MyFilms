package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thefear.myfilms.*
import com.thefear.myfilms.databinding.FragmentFilmListBinding
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.entities.Film
import com.thefear.myfilms.ui.viewmodels.FilmsListViewModel
import com.thefear.myfilms.ui.adapters.FilmsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmListFragment : Fragment() {

    private val viewModel: FilmsListViewModel by viewModel()
    private var _binding: FragmentFilmListBinding? = null
    private val binding get() = _binding!!

    private var adapter: FilmsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveDataMove().observe(viewLifecycleOwner, observer)
        viewModel.getLiveDataTV().observe(viewLifecycleOwner, observer)
        viewModel.getLiveDataCartoon().observe(viewLifecycleOwner, observer)
        viewModel.getLiveDataAnime().observe(viewLifecycleOwner, observer)
        viewModel.getServerFilms()

        listToolbar.setOnMenuItemClickListener {
            val manager = activity?.supportFragmentManager
            when(it.itemId) {
                R.id.action_favorite -> {
                    manager?.let {
                        manager.beginTransaction()
                            .replace(R.id.fragmentContainer, FavoriteFragment.newInstance())
                            .addToBackStack(null)
                            .commit()
                    }
                    true
                }
                R.id.action_see_later -> {
                    true
                }
                R.id.action_settings -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessMove -> {
                val filmsData = appState.filmsData
                updateRecycler(moveRecyclerView, filmsData)
                progressBarMove.hide()
                tryAgainContainerMove.hide()
                noUsersTextView.hide()
            }
            is AppState.SuccessTV -> {
                val filmsData = appState.filmsData
                updateRecycler(tvSeriesRecyclerView, filmsData)
            }
            is AppState.SuccessCartoon -> {
                val filmsData = appState.filmsData
                updateRecycler(cartoonRecyclerView, filmsData)
            }
            is AppState.SuccessAnime -> {
                val filmsData = appState.filmsData
                updateRecycler(animeRecyclerView, filmsData)
            }
            is AppState.Error -> {
                moveRecyclerView.hide()
                progressBarMove.hide()
                tryAgainContainerMove.show()
                noUsersTextView.show()
                fragmentFilmList.snack(
                    R.string.errorLoading.toString(),
                    R.string.reloadError.toString(),
                    viewModel.getServerFilms()
                )
            }
            is AppState.Loading -> {
                moveRecyclerView.hide()
                progressBarMove.show()
                tryAgainContainerMove.hide()
                noUsersTextView.hide()
            }
        }

    }

    private fun updateRecycler(view: RecyclerView, filmsData: MutableList<Film>) = with(binding) {
        view.show()
        adapter = FilmsAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(film: Film) {
                val manager = activity?.supportFragmentManager
                manager?.let { _ ->
                    val bundle = Bundle().apply {
                        putParcelable(FilmDetailsFragment.FILM_EXTRA, film)
                    }
                    manager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FilmDetailsFragment.newInstance(bundle)
                        )
                        .addToBackStack(null)
                        .commit()
                }
            }

        })
        view.adapter = adapter
        view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        setData(filmsData)
    }

    private fun setData(filmsData: MutableList<Film>) = with(binding) {
        viewModel.getLiveDataMove()
        adapter?.films = filmsData
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

    companion object {
        fun newInstance() = FilmListFragment()
    }


}