package com.setianjay.watchme.presentation.home.content.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.watchme.core.data.Resource
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.utils.ViewUtil.show
import com.setianjay.watchme.databinding.FragmentMoviesBinding
import com.setianjay.watchme.core.presentation.adapter.HorizontalMovieAdapter
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.presentation.adapter.VerticalMovieAdapter
import com.setianjay.watchme.presentation.detail.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnMovieAdapterListener {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding
    private val moviesViewModel: MoviesViewModel by viewModels()

    private lateinit var moviesHorizontalAdapter: HorizontalMovieAdapter
    private lateinit var moviesVerticalAdapter: VerticalMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMoviesNowPlaying()
        setupMoviesPopular()
        setupObserver()
    }

    /**
     * setup content movies now playing
     * */
    private fun setupMoviesNowPlaying() {
        moviesHorizontalAdapter = HorizontalMovieAdapter(this)

        binding?.rvMovieHorizontal?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = moviesHorizontalAdapter
            setHasFixedSize(true)
        }
    }

    /**
     * setup content movies popular
     * */
    private fun setupMoviesPopular() {
        moviesVerticalAdapter = VerticalMovieAdapter(requireContext(), this)

        binding?.rvMovieVertical?.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = moviesVerticalAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObserver() {
        /* now playing */
        moviesViewModel.getMoviesNowPlaying().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding?.pbNowPlaying?.show(true)
                }
                is Resource.Success -> {
                    binding?.pbNowPlaying?.show(false)
                    result.data?.let { moviesNowPlaying ->
                        moviesHorizontalAdapter.setDataMovie(moviesNowPlaying)
                    }
                }
                is Resource.Error -> {
                    binding?.pbNowPlaying?.show(false)
                }
            }
        }

        /* popular */
        moviesViewModel.getMoviesPopular().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding?.pbPopular?.show(true)
                }
                is Resource.Success -> {
                    binding?.pbPopular?.show(false)
                    result.data?.let { moviesPopular ->
                        moviesVerticalAdapter.setDataMovie(moviesPopular)
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