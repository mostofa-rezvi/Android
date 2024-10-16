package com.example.foodzilla3

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class SettingActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var rgLanguage: RadioGroup
    lateinit var rgPayment: RadioGroup
    lateinit var btnSave: Button
    lateinit var switchNotifications: Switch

    companion object {
        const val PREFS_NAME = "RestaurantPrefs"
        const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
        const val KEY_PAYMENT_OPTIONS = "payment_options"
        const val KEY_LANGUAGE = "language"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val toolbar: Toolbar = findViewById(R.id.toolbar5)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title ="Settings"
        rgLanguage = findViewById(R.id.rgLanguage)
        rgPayment = findViewById(R.id.rgSetPayments)
        btnSave = findViewById(R.id.btnSaveSetting)
        switchNotifications = findViewById(R.id.sNotification)
        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadPreferences()
        btnSave.setOnClickListener {
            savePreferences()
        }
    }

    private fun loadPreferences() {
        val notificationsEnabled = sharedPreferences.getBoolean(KEY_NOTIFICATIONS_ENABLED, false)
        val paymentOptions =
            sharedPreferences.getString(KEY_PAYMENT_OPTIONS, "Upi")
        val language = sharedPreferences.getString(KEY_LANGUAGE, "English")

        switchNotifications.isChecked = notificationsEnabled
        if (language=="English") {
            rgLanguage.check(R.id.rbEnglish)
        }
        else{
            rgLanguage.check(R.id.rbHindi)
        }
        when(paymentOptions){
            "Upi"->rgPayment.check(R.id.rbUpi)
            "Net"->rgPayment.check(R.id.rbNetBanking)
            "Others"->rgPayment.check(R.id.rbOthers)
            "Card"->rgPayment.check(R.id.rbCard)
        }
    }

    private fun savePreferences() {
        val notificationsEnabled = switchNotifications.isChecked

        var paymentOptions = "Upi"

        var language = "English"
        if (rgLanguage.checkedRadioButtonId == R.id.rbEnglish) {
            language = "English"

        } else {
            language = "Hindi"
        }
        when(rgPayment.checkedRadioButtonId){
            R.id.rbUpi->paymentOptions="Upi"
            R.id.rbCard->paymentOptions="Card"
            R.id.rbNetBanking->paymentOptions="Net"
            R.id.rbOthers->paymentOptions="Others"
        }
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_NOTIFICATIONS_ENABLED, notificationsEnabled)
        editor.putString(KEY_PAYMENT_OPTIONS, paymentOptions)
        editor.putString(KEY_LANGUAGE, language)
        editor.apply()
        Toast.makeText(this, "Preferences saved successfully!", Toast.LENGTH_SHORT).show()
    }
}