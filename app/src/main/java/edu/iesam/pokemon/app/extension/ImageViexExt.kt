package edu.iesam.pokemon.app.extension

import android.widget.ImageView
import coil.load

/*
Esto con glide
fun ImageView.loadUrl(url: String) {
    Glide
        .with(this)
        .load(url)
        .into(this)
}
 */

fun ImageView.loadUrl(url: String) {
    this.load(url)
}