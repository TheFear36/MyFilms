package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thefear.myfilms.databinding.FragmentFavoriteBinding
import com.thefear.myfilms.model.BDState
import com.thefear.myfilms.ui.adapters.FavoriteMoveAdapter
import com.thefear.myfilms.ui.viewmodels.HistoryViewModel
import org.koin.android.ext.android.inject

class FavoriteFragment: Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by inject()
    private val adapter: FavoriteMoveAdapter by lazy { FavoriteMoveAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        downloadFavoriteMoves()
    }

    private fun downloadFavoriteMoves() = with(binding) {
        favoriteRecycler.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner,{ renderData(it) })
        viewModel.getAllHistory()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun renderData(bdState: BDState) {
        when(bdState) {
            is BDState.Success -> {
                adapter.films = bdState.filmsData
            }
            is BDState.Loading -> {
                //TODO
            }
            is BDState.Error -> {
                //TODO
            }
        }
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}