package com.bilgiland.movielist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bilgiland.movielist.ConstValue.MOVIE_ID
import com.bilgiland.movielist.R

class MovieDetailFragment :Fragment(R.layout.detail_fragment)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("tag", "long is ${arguments?.getLong(MOVIE_ID)}")
    }
}