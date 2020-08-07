package com.impact.assistantapp.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.impact.assistantapp.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}