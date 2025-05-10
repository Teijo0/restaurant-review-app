package com.example.restaurantreviewapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Sovellustason Application-luokka, jossa otetaan Hilt käyttöön.
 * Tämä tarvitaan Hiltin toimintaan projektissa.
 */
@HiltAndroidApp
class MyApplication : Application()
