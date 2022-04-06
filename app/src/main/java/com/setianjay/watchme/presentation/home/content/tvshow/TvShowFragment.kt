package com.setianjay.watchme.presentation.home.content.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.HorizontalMovieAdapter
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.presentation.adapter.VerticalMovieAdapter
import com.setianjay.watchme.core.utils.ViewUtil.show
import com.setianjay.watchme.databinding.FragmentTvShowBinding
import com.setianjay.watchme.presentation.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowFragment : Fragment(), OnMovieAdapterListener {
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding
    private val tvViewModel: TvShowViewModel by viewModels()

    private lateinit var tvHorizontalAdapter: HorizontalMovieAdapter
    private lateinit var tvVerticalAdapter: VerticalMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTvNowPlaying()
        setupTvPopular()
        setupObserver()
    }

    /**
     * setup content tv now playing
     * */
    private fun setupTvNowPlaying(){
        tvHorizontalAdapter = HorizontalMovieAdapter(this)

        binding?.rvMovieHorizontal?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = tvHorizontalAdapter
            setHasFixedSize(true)
        }
    }

    /***
     * setup content tv popular
     */
    private fun setupTvPopular(){
        tvVerticalAdapter = VerticalMovieAdapter(requireContext(), this)

        binding?.rvMovieVertical?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = tvVerticalAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObserver(){
        /* now playing */
        tvViewModel.getTvNowPlaying().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding?.pbNowPlaying?.show(true)
                }
                is Resource.Success -> {
                    binding?.pbNowPlaying?.show(false)
                    result.data?.let { moviesNowPlaying ->
                        tvHorizontalAdapter.setDataMovie(moviesNowPlaying)
                    }
                }
                is Resource.Error -> {
                    binding?.pbNowPlaying?.show(false)
                }
            }
        }

        /* popular */
        tvViewModel.getTvPopular().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding?.pbPopular?.show(true)
                }
                is Resource.Success -> {
                    binding?.pbPopular?.show(false)
                    result.data?.let { moviesPopular ->
                        tvVerticalAdapter.setDataMovie(moviesPopular)
                    }
                }
                is Resource.Error -> {
                    binding?.pbPopular?.show(false)
                }
            }
        }
    }

    /**
     * listener when content has clicked
     *
     * @param movie     data of movie
     * */
    override fun onClick(movie: Movie) {
        requireActivity().startActivity(
            DetailMovieActivity.navigateToDetailMovieActivity(
                requireContext(),
                movie
            )
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}