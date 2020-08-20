package com.bilgiland.movielist.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgiland.movielist.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        movieAdapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                progressBar.visibility = View.VISIBLE;
            } else {
                progressBar.visibility = View.GONE;
            }
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(context,2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }


        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }


    }
}