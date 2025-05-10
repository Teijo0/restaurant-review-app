// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Korjattu buildscript ja riippuvuudet
buildscript {
    // Määritellään suoraan muuttuja
    val hiltVersion = "2.48"

    dependencies {
        // Käytetään suoraan muuttujaa hiltVersion
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}

