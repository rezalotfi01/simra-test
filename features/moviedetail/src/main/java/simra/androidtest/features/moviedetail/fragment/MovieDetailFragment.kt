package simra.androidtest.features.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import simra.androidtest.features.moviedetail.databinding.FragmentMovieDetailBinding
import simra.androidtest.features.moviedetail.viewmodel.MovieDetailViewModel
import simra.androidtest.common.base.BaseFragment
import simra.androidtest.common.base.BaseViewModel
import simra.androidtest.common.extension.shareText
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailFragment : BaseFragment() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private lateinit var binding: FragmentMovieDetailBinding

    private val navArgs: MovieDetailFragmentArgs by navArgs()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        configureUi()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureObserver()
        binding.viewmodel?.getMovie(navArgs.id)
    }

    private fun configureUi() {
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun configureObserver() {
        binding.viewmodel?.shareableContent?.observe(viewLifecycleOwner) {
            requireActivity().shareText(it)
        }
    }

}