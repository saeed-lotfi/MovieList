package com.bilgiland.movielist.ui

/**
 * click listener on activity for adapter
 */
interface ClickListener {

    /**
     * transfer value from recyclerview to activity
     */
    fun clicked(value:Long?)

}