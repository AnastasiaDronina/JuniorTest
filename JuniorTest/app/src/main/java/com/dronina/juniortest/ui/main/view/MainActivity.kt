package com.dronina.juniortest.ui.main.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dronina.juniortest.R
import com.dronina.juniortest.ui.base.BaseViewModelFactory.Companion.getViewModel
import com.dronina.juniortest.ui.main.viewmodel.MainViewModel
import com.dronina.juniortest.utils.ui.navigate

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        getViewModel { MainViewModel() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()

        if (savedInstanceState == null) {
            navigate(destination = MedsFragment(), addToBackStack = false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupUi() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.hide()
    }
}