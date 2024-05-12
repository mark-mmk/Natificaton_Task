package com.example.natificaton_task

import android.content.Context
import java.util.Locale

class localHelper {
    fun setLocal(context: Context, language: String) {
        val newLocal = Locale(language)
        Locale.setDefault(newLocal)
        val resources = context.resources
        val config = resources.configuration
        config.setLocale(newLocal)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}