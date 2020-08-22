package com.bilgiland.movielist.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.bilgiland.movielist.ConstValue.MOVIE_ID
import com.bilgiland.movielist.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_fragment.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_fragment), ClickListener {


    var movieAdapter: MovieAdapter = MovieAdapter(this)

    private val viewModel: MainViewModel by viewModels()

    private lateinit var rotate: Animation


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initObj()

        initRecyclerView()

        gettingData()

        manageClick()

    }

    // manage click
    private fun manageClick() {

        image_header.setOnClickListener {
            lifecycleScope.launch {
                movieAdapter.submitData(PagingData.empty())
                viewModel.clearData()
            }
            it.startAnimation(rotate)
        }
    }

    //initialize object
    private fun initObj() {
        rotate = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)

    }

    // getting data from net
    private fun gettingData() {
        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->

                movieAdapter.submitData(pagingData)
            }
        }

    }


    // initialize recyclerView
    private fun initRecyclerView() {
        recyclerView.apply {

            val mLayoutManager = GridLayoutManager(context, 2)

            // if it's loading or error just show as once
            mLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == movieAdapter.itemCount) {
                        if (position % 2 == 0)
                            2
                        else
                            1
                    } else
                        1
                }
            }

            recyclerView.layoutManager = mLayoutManager
            adapter = movieAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
        }
    }

    override fun clicked(value: Long?) {

        val movieId = Bundle()
        movieId.putLong(MOVIE_ID, value ?: 0)

        findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, movieId)
    }

}