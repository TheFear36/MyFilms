package com.thefear.myfilms

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.show(): View {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
    return this
}

fun View.hide(): View {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
    return this
}

fun View.snack(text: String, actionText: String, action: Unit){
    Snackbar.make(this, text, Snackbar.LENGTH_INDEFINITE)
        .setAction(actionText) { action }
        .show()
}