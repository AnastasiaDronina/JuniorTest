package com.dronina.juniortest.utils.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dronina.juniortest.R
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(text: Int, action: Int) {
    Snackbar.make(this, text, Snackbar.LENGTH_LONG)
        .setAction(action) { }
        .setActionTextColor(resources.getColor(R.color.secondary))
        .show()
}

fun setGone(gone: Boolean, vararg views: View) {
    views.forEach { view ->
        view.visibility = if (gone)
            View.GONE
        else
            View.VISIBLE
    }
}

fun setInvisible(invisible: Boolean, vararg views: View) {
    views.forEach { view ->
        view.visibility = if (invisible)
            View.INVISIBLE
        else
            View.VISIBLE
    }
}

fun AppCompatActivity.navigate(
    destination: Fragment,
    arguments: Bundle? = null,
    addToBackStack: Boolean = true
) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    destination.arguments = arguments
    fragmentTransaction.replace(R.id.container, destination)
    if (addToBackStack) fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
}