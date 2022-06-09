package org.sopt.diablo.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context) {
    private val ACCOUNT: String = "account"

    private val accountPrefs: SharedPreferences =
        context.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE)

    fun getAutoLogin(): Boolean = accountPrefs.getBoolean("autoLogin", false)
    fun setAutoLogin() = accountPrefs.edit().putBoolean("autoLogin", true).apply()

    fun getOnboardingActivation(): Boolean = accountPrefs.getBoolean("onboarding", true)
    fun setOnboardingDisabled() = accountPrefs.edit().putBoolean("onboarding", false).apply()

    fun getId(): String = accountPrefs.getString("id", "").toString()
    fun setId(str: String) = accountPrefs.edit().putString("id", str).apply()

    fun getName(): String = accountPrefs.getString("name", "").toString()
    fun setName(str: String) = accountPrefs.edit().putString("name", str).apply()

}