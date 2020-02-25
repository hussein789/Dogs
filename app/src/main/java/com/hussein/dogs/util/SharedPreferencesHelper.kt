package com.hussein.dogs.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class SharedPreferencesHelper {
    companion object{
        private const val PREF_TIME = "Pref Time"
        private var pref:SharedPreferences? = null
        private val LOCK = Any()
        @Volatile private var instance:SharedPreferencesHelper? = null
        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
            instance ?: buildHelper(context).also{
                instance = it
            }
        }

        private fun buildHelper(context: Context):SharedPreferencesHelper{
            pref = PreferenceManager.getDefaultSharedPreferences(context)
            return SharedPreferencesHelper()
        }
    }

    fun saveUpdateTime(time:Long){
        pref?.edit(commit = true){
            putLong(PREF_TIME,time)
        }
    }

    fun getUpdateTime() = pref?.getLong(PREF_TIME,0L)
}