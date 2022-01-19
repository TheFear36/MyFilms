package com.thefear.myfilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.thefear.myfilms.databinding.ActivityMainBinding
import com.thefear.myfilms.ui.screens.FilmListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FilmListFragment.newInstance())
                .commitNow()
        }
    }
}