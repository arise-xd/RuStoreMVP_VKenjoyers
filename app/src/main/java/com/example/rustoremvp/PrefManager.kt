package com.example.rustoremvp

import android.content.Context
import android.content.SharedPreferences

class PrefManager(context: Context) {
    private val PREF_NAME = "ru_store_prefs"
    private val KEY_ONBOARDING_DONE = "onboarding_done"
    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    fun isFirstLaunch() = !prefs.getBoolean(KEY_ONBOARDING_DONE, false)

    fun setOnboardingDone() {
        prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply()
    }
}