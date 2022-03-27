package com.setianjay.watchme.favorite.presentation.content

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.watchme.core.domain.model.Movie
import com.setianjay.watchme.core.presentation.adapter.OnMovieAdapterListener
import com.setianjay.watchme.core.presentation.adapter.VerticalMovieAdapter
import com.setianjay.watchme.di.module.FavoriteModuleDependencies
import com.setianjay.watchme.favorite.databinding.FragmentFavoriteMovieBinding
import com.setianjay.watchme.favorite.di.DaggerFavoriteComponent
import com.setianjay.watchme.presentation.detail.DetailMovieActivity
import com.setianjay.watchme.favorite.utils.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteMovieFragment(private val isMovie: Boolean) : Fragment() {
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding

    private lateinit var verticalMovieAdapter: VerticalMovieAdapter

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteMovieViewModel by viewModels<FavoriteMovieViewModel> {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //inject dagger into this fragment
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMovieFavorite()
        setupObserver()
    }

    /**
     * setup content movie favorite
     * */
    private fun setupMovieFavorite() {
        verticalMovieAdapter =
            VerticalMovieAdapter(requireContext(), object : OnMovieAdapterListener {
                override fun onClick(movie: Movie) {
                    requireActivity().startActivity(
                        DetailMovieActivity.navigateToDetailMovieActivity(requireContext(), movie)
                    )
                }
            })

        binding?.rvFavorite?.apply {
            adapter = verticalMovieAdapter
            layoutManager =
                LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    /**
     * setup observer for observe value from view model
     * */
    private fun setupObserver() {
        if (isMovie) {
            favoriteMovieViewModel.getMoviesFavorite().observe(viewLifecycleOwner) { result ->
                if (result.isNotEmpty()) {
                    verticalMovieAdapter.setDataMovie(result)
                    showImgInformation(false)
                }else{
                    showImgInformation(true)
                }
            }
        } else {
            favoriteMovieViewModel.getTvFavorite().observe(viewLifecycleOwner) { result ->
                if (result.isNotEmpty()) {
                    verticalMovieAdapter.setDataMovie(result)
                    showImgInformation(false)
                }else{
                    showImgInformation(true)
                }
            }
        }
    }

    /**
     * to show or hide image information if list data favorite is empty
     *
     * @param isEmpty       true means data is empty, otherwise not empty
     * */
    private fun showImgInformation(isEmpty: Boolean) {
        if (isEmpty){
            binding?.apply {
                ivEmpty.visibility = View.VISIBLE
                tvMessage.visibility = View.VISIBLE
                rvFavorite.visibility = View.GONE
            }
        }else{
            binding?.apply {
                ivEmpty.visibility = View.GONE
                tvMessage.visibility = View.GONE
                rvFavorite.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}