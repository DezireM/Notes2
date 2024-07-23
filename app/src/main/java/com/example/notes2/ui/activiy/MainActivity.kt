package com.example.notes2.ui.activiy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes2.R
import com.example.notes2.databinding.ActivityMainBinding
import com.example.notes2.utils.PreferenceHelper


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferenceHelper: PreferenceHelper

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController

        sharedPreferenceHelper = PreferenceHelper(this@MainActivity)

        if (!sharedPreferenceHelper.isOnBoardingComplete()) {
            sharedPreferenceHelper.setOnBoardingComplete(true)
        } else {
            navController.navigate(R.id.noteFragment)
        }
    }
}