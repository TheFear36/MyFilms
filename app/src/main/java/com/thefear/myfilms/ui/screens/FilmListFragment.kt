package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.thefear.myfilms.FilmsAdapter
import com.thefear.myfilms.R
import com.thefear.myfilms.ui.FilmsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.thefear.myfilms.databinding.FragmentFilmListBinding
import com.thefear.myfilms.model.AppState
import com.thefear.myfilms.model.entities.Film

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
        viewModel.getFilms()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Success -> {
                val filmsData = appState.filmsData
                recyclerView.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                tryAgainContainer.visibility = View.GONE
                noUsersTextView.visibility = View.GONE
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
                recyclerView.adapter = adapter
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                setData(filmsData)
            }
            is AppState.Error -> {
                recyclerView.visibility = View.GONE
                progressBar.visibility = View.GONE
                tryAgainContainer.visibility = View.VISIBLE
                noUsersTextView.visibility = View.VISIBLE
                Snackbar.make(fragmentFilmList, R.string.errorLoading, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.reloadError) { viewModel.getFilms() }
                    .show()
            }
            is AppState.Loading -> {
                recyclerView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                tryAgainContainer.visibility = View.GONE
                noUsersTextView.visibility = View.GONE
            }
        }

    }

    private fun setData(filmsData: MutableList<Film>) = with(binding) {
        viewModel.getLiveData()
        adapter?.films = filmsData
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(film: Film)
    }

    companion object {
        fun newInstance() = FilmListFragment()
    }


}