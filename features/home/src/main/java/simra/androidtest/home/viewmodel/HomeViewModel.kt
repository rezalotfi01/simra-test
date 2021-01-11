package simra.androidtest.home.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import simra.androidtest.common.base.BaseViewModel
import simra.androidtest.common.utils.DateUtils
import simra.androidtest.common.utils.Event
import simra.androidtest.home.R
import simra.androidtest.home.domain.SearchMoviesUseCase
import simra.androidtest.model.Movie
import simra.androidtest.repository.AppDispatchers
import simra.androidtest.repository.utils.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import simra.androidtest.home.fragment.HomeFragmentDirections

class HomeViewModel(private val searchMoviesUseCase: SearchMoviesUseCase,
                    private val dispatchers: AppDispatchers
) : BaseViewModel() {
    val greetingMessage = ObservableField(DateUtils.getTimeGreetingMessage())

    private var currentPage = 1

    private val _movies = MediatorLiveData<Resource<MutableList<Movie>>>()
    val movies: LiveData<Resource<MutableList<Movie>>> get() = _movies

    val titleSearch = MutableLiveData<String>()

    private val _showEmpty = MutableLiveData<Boolean>()
    val showEmpty: LiveData<Boolean> get() = _showEmpty

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private var moviesSource: LiveData<Resource<MutableList<Movie>>> = MutableLiveData()

    init {
        searchMovies(titleSearch.value?: "", false)
    }

    fun userClicksOnMovie(movie: Movie) {
        navigate(HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id))
    }

    fun userClickOnMoviePoster(movie: Movie) {
        navigate(HomeFragmentDirections.actionHomeFragmentToImageDetailFragment(movie.poster))
    }

    fun userRefreshesItems()
            = searchMovies(titleSearch.value?: "", false)

    fun onScrolledToBottom(){
        if (currentPage <= movies.value?.data?.size?: 0 / 10) {
            currentPage++
            searchMovies(titleSearch.value?: "", true, currentPage)
        }

    }

    fun searchMovies(title: String, isPagination: Boolean, page: Int = 1) = viewModelScope.launch(dispatchers.main) {
        _movies.removeSource(moviesSource)
        withContext(dispatchers.io) { moviesSource = searchMoviesUseCase(title = title, page = page.toString()) }
        if (!moviesSource.hasActiveObservers()) {
            _movies.addSource(moviesSource) { newData ->
                if (!isPagination){
                    currentPage = 1
                    _showEmpty.postValue(newData.data?.isEmpty() != false)
                    _showLoading.postValue(newData.status == Resource.Status.LOADING)
                    _movies.value = newData
                } else {
                    newData.data?.let {
                        val oldData = movies.value?.data
                        if (it != oldData) {
                            oldData?.addAll(it)
                            _movies.value = Resource.success(oldData)
                        }
                    }
                }
                if (newData.status == Resource.Status.ERROR) _snackbarError.value = Event(R.string.error_happened)
            }
        }
    }
}