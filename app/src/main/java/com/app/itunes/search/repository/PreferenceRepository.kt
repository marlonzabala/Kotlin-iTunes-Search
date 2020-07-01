package com.app.itunes.search.repository

import android.content.Context
import android.content.SharedPreferences

/**
 * Repository for handling persiting information
 */
class PreferenceRepository(context: Context) {
    private val PRIVATE_MODE = 0
    private val PREF_NAME = "data"
    private val LAST_SEARCH_TERM = "last_search_term"
    private val LAST_POSITION = "last_position"
    private val IS_VIEWING_DESCRIPTION = "is_viewing_description"

    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun getLastSearchTerm() : String {
        return sharedPref.getString(LAST_SEARCH_TERM,"").toString()
    }

    fun setLastSearchTerm(term : String) {
        sharedPref.edit().putString(LAST_SEARCH_TERM,term).apply()
    }

    fun getLastPosition() : Int {
        return sharedPref.getInt(LAST_POSITION,0)
    }

    fun setLastPosition(position : Int) {
        sharedPref.edit().putInt(LAST_POSITION,position).apply()
    }

    fun getIsViewingDetails() : Boolean {
        return sharedPref.getBoolean(IS_VIEWING_DESCRIPTION,false)
    }

    fun setIsViewingDetails(isViewing : Boolean) {
        sharedPref.edit().putBoolean(IS_VIEWING_DESCRIPTION, isViewing).apply()
    }
}