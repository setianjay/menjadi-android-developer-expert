package com.setianjay.watchme.search.presentation

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.watchme.core.constants.RemoteConst
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.utils.ViewUtil.show
import com.setianjay.watchme.di.module.SearchModuleDependencies
import com.setianjay.watchme.presentation.detail.DetailMovieActivity
import com.setianjay.watchme.presentation.home.HomeActivity
import com.setianjay.watchme.search.R
import com.setianjay.watchme.search.databinding.ActivitySearchBinding
import com.setianjay.watchme.search.di.DaggerSearchComponent
import com.setianjay.watchme.search.presentation.adapter.SearchResultAdapter
import com.setianjay.watchme.search.utils.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private var searchType: Int = 0
    private lateinit var searchResultAdapter: SearchResultAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val searchViewModel by viewModels<SearchViewModel> {
        factory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerSearchComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    SearchModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupData()
        setupObserver()
    }

    private fun setupData() {
        searchType = intent.getIntExtra(HomeActivity.SEARCH_EXTRA, 0)
        doSearch()
        initialView()
        setupRecyclerView()
    }

    private fun initialView() {
        binding.etSearch.hint =
            if (searchType == SEARCH_TYPE_MOVIES) resources.getString(R.string.hint_search_movies)
            else resources.getString(R.string.hint_search_tv)
    }

    private fun doSearch() {
        binding.etSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val movieTitle = binding.etSearch.text.toString().trim()
                if (movieTitle.isEmpty()) {
                    binding.etSearch.error = resources.getString(R.string.field_required)
                } else {
                    if (searchType == SEARCH_TYPE_MOVIES) {
                        searchViewModel.searchMoviesByTitle(movieTitle)
                    } else {
                        searchViewModel.searchTvByTitle(movieTitle)
                    }
                    return@setOnEditorActionListener false
                }
            }
            false
        }
    }

    private fun setupRecyclerView() {
        searchResultAdapter = SearchResultAdapter(object : OnMovieAdapterListener {
            override fun onClick(movie: Movie) {
                startActivity(
                    DetailMovieActivity.navigateToDetailMovieActivity(this@SearchActivity, movie)
                )
            }

        })
        binding.rvSearch.apply {
            adapter = searchResultAdapter
            layoutManager =
                LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupObserver(){
        searchViewModel.getMovie().observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.pbSearch.show(true)
                    binding.layoutError.root.show(false)
                    binding.layoutEmpty.root.show(false)
                }
                is Resource.Success -> {
                    binding.pbSearch.show(false)
                    binding.rvSearch.show(true)
                    result.data?.let {
                        searchResultAdapter.setSearchResult(it)
                    }
                }
                is Resource.Error -> {
                    binding.pbSearch.show(false)
                    binding.rvSearch.show(false)
                    result.errorCode?.let { errorCode ->
                        showMessageBasedOnErrorCode(errorCode)
                    }
                }
            }
        }
    }

    private fun showMessageBasedOnErrorCode(errorCode: Int) {
        when (errorCode) {
            RemoteConst.ERR_CODE_API -> {
                binding.layoutError.root.show(true)
                binding.layoutError.root.setOnClickListener {
                    val movieTitle = binding.etSearch.text.toString().trim()
                    if (searchType == SEARCH_TYPE_MOVIES) {
                        searchViewModel.searchMoviesByTitle(movieTitle)
                    } else {
                        searchViewModel.searchMoviesByTitle(movieTitle)
                    }
                }
            }
            RemoteConst.ERR_CODE_EMPTY -> {
                binding.layoutEmpty.root.show(true)
            }
        }
    }

    companion object {
        private const val SEARCH_TYPE_MOVIES = 0
    }
}