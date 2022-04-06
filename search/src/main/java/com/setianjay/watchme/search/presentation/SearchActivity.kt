package com.setianjay.watchme.search.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ObsoleteCoroutinesApi
@FlowPreview
@ExperimentalCoroutinesApi
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private var searchType: Int = 0
    private lateinit var searchResultAdapter: SearchResultAdapter

    @Inject
    lateinit var factory: ViewModelFactory

    @FlowPreview
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
        val mTextViewTextChangedListener = TextViewTextChangedListener(object: TextViewTextChangedCallback{
            override fun afterTextChanged(text: String) {
                lifecycleScope.launch {
                    searchViewModel.queryChannel.send(text)
                }
            }
        })

        binding.etSearch.addTextChangedListener(mTextViewTextChangedListener)
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

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun setupObserver() {
        searchViewModel.getMovie(searchType).observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    //not implemented
                }
                is Resource.Success -> {
                    binding.layoutEmpty.root.show(false)
                    binding.rvSearch.show(true)
                    result.data?.let {
                        searchResultAdapter.setSearchResult(it)
                    }
                }
                is Resource.Error -> {
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
                /* not implemented */
            }
            RemoteConst.ERR_CODE_EMPTY -> {
                binding.rvSearch.show(false)
                binding.layoutEmpty.root.show(true)
            }
        }
    }

    companion object {
        private const val SEARCH_TYPE_MOVIES = 0
    }
}