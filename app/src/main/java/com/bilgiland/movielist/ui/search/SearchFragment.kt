package com.bilgiland.movielist.ui.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import com.bilgiland.movielist.ConstValue
import com.bilgiland.movielist.R
import com.bilgiland.movielist.ui.ClickListener
import com.bilgiland.movielist.ui.MovieAdapter
import com.bilgiland.movielist.ui.MovieLoadStateAdapter
import com.bilgiland.movielist.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search), ClickListener {


    var movieAdapter: MovieAdapter = MovieAdapter(this)

    private val viewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRecyclerView()

        edt_search.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                clearData()
                gettingData(edt_search.text.toString())

                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun clearData() {
        lifecycleScope.launch {
            movieAdapter.submitData(PagingData.empty())
            viewModel.clearData()
            activity?.hideKeyboard()
        }
    }


    // getting data from net
    private fun gettingData(searchQuery: String) {
        lifecycleScope.launch {
            viewModel.searchMovie(searchQuery).collectLatest { pagingData ->

                movieAdapter.submitData(pagingData)
            }
        }

    }


    // initialize recyclerView
    private fun initRecyclerView() {
        rec_search.apply {

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

            rec_search.layoutManager = mLayoutManager
            adapter = movieAdapter.withMyFooter(
                footer = MovieLoadStateAdapter { movieAdapter.retry() }
            )
        }
    }

    override fun clicked(value: Long?) {

        val movieId = Bundle()
        movieId.putLong(ConstValue.MOVIE_ID, value ?: 0)

        findNavController().navigate(R.id.action_searchFragment_to_movieDetailFragment, movieId)
    }


}