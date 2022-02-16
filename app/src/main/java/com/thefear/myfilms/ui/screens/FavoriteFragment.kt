package com.thefear.myfilms.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.thefear.myfilms.databinding.FragmentFavoriteBinding
import com.thefear.myfilms.ui.adapters.FavoriteMoveAdapter

class FavoriteFragment: Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private var adapter: FavoriteMoveAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        downloadFavoriteMoves()
    }

    private fun downloadFavoriteMoves() {
        adapter?.films
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}