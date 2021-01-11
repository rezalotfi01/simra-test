package simra.androidtest.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import simra.androidtest.common.base.BaseFragment
import simra.androidtest.common.base.BaseViewModel
import simra.androidtest.common.extension.onScrollToEnd
import simra.androidtest.home.view.MovieAdapter
import simra.androidtest.home.databinding.FragmentHomeBinding
import simra.androidtest.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(){

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureUi()
        configureObserver()
        configureEvent()
    }

    private fun configureEvent(){
        binding.recyclerView.onScrollToEnd(3) {
            binding.viewmodel?.onScrolledToBottom()
        }
    }

    private fun configureObserver(){
        binding.viewmodel?.titleSearch?.observe(viewLifecycleOwner, {
            binding.viewmodel?.searchMovies(it, false)
        })
    }

    private fun configureUi() {
        binding.editTextName.requestFocus()
        binding.recyclerView.adapter = MovieAdapter(viewModel)
    }
}