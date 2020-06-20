package com.saldi.trendinglivedata.data.persist

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import android.preference.PreferenceManager
import javax.inject.Inject

open class TinyDB @Inject constructor(appContext: Context?) {
    private val preferences: SharedPreferences

    /**
     * Get long value from SharedPreferences at 'key'. If key not found, return 'defaultValue'
     * @param key SharedPreferences key
     * @param defaultValue long value returned if key was not found
     * @return long value at 'key' or 'defaultValue' if key not found
     */
    fun getLong(key: String?, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    /**
     * Put long value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param value long value to be added
     */
    fun putLong(key: String?, value: Long) {
        checkForNullKey(key)
        preferences.edit().putLong(key, value).apply()
    }

    /**
     * Put String value into SharedPreferences with 'key' and save
     * @param key SharedPreferences key
     * @param value String value to be added
     */
    fun putString(key: String?, value: String?) {
        checkForNullKey(key)
        checkForNullValue(value)
        preferences.edit().putString(key, value).apply()
    }


    fun remove(key: String?) {
        preferences.edit().remove(key).apply()
    }


    /**
     * Clear SharedPreferences (remove everything)
     */
    fun clear() {
        preferences.edit().clear().apply()
    }

    /**
     * Retrieve all values from SharedPreferences. Do not modify collection return by method
     * @return a Map representing a list of key/value pairs from SharedPreferences
     */
    val all: Map<String, *>
        get() = preferences.all

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param the pref key
     */
    fun checkForNullKey(key: String?) {
        if (key == null) {
            throw NullPointerException()
        }
    }

    /**
     * null keys would corrupt the shared pref file and make them unreadable this is a preventive measure
     * @param the pref key
     */
    fun checkForNullValue(value: String?) {
        if (value == null) {
            throw NullPointerException()
        }
    }

    companion object {
        /**
         * Check if external storage is writable or not
         * @return true if writable, false otherwise
         */
        val isExternalStorageWritable: Boolean
            get() = Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()

        /**
         * Check if external storage is readable or not
         * @return true if readable, false otherwise
         */
        val isExternalStorageReadable: Boolean
            get() {
                val state = Environment.getExternalStorageState()
                return Environment.MEDIA_MOUNTED == state || Environment.MEDIA_MOUNTED_READ_ONLY == state
            }
    }

    init {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext)
    }
}