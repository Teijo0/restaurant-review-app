package com.example.restaurantreviewapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlin.text.Typography.dagger

/**
 * Sovellustason Application-luokka, jossa otetaan Hilt käyttöön.
 */
@HiltAndroidApp
class MyApplication : Application()