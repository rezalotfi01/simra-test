package simra.androidtest.features.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import simra.androidtest.features.moviedetail.R
import simra.androidtest.features.moviedetail.domain.GetMovieUseCase
import simra.androidtest.common.base.BaseViewModel
import simra.androidtest.model.Movie
import simra.androidtest.repository.AppDispatchers
import simra.androidtest.repository.utils.Resource
import simra.androidtest.repository.utils.Resource.Status
import simra.androidtest.common.utils.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(private val getMovieUseCase: GetMovieUseCase,
                           private val dispatchers: AppDispatchers
) : BaseViewModel() {
    private lateinit var currentId: String

    private val _movie = MediatorLiveData<Movie>()
    val movie: LiveData<Movie> get() = _movie

    private val _showLoading = MutableLiveData<Boolean>()
    val showLoading: LiveData<Boolean> get() = _showLoading

    private val _shareableContent = MutableLiveData<String>()
    val shareableContent: LiveData<String> = _shareableContent

    private var movieSource: LiveData<Resource<Movie>> = MutableLiveData()

    fun userRefreshes(){
        getMovie(currentId)
    }

    fun getMovie(id: String) = viewModelScope.launch(dispatchers.main) {
        currentId = id
        _movie.removeSource(movieSource)
        withContext(dispatchers.io) { movieSource = getMovieUseCase(id) }
        if (!movieSource.hasActiveObservers()) {
            _movie.addSource(movieSource) {
                _showLoading.postValue(it.status == Status.LOADING)
                _movie.postValue(it.data)
                if (it.status == Status.ERROR) _snackbarError.value = Event(R.string.error_happened)
            }
        }
    }

    fun shareContent(){
        if (movie.value == null)
            return
        val content = movie.value!!.title + "\n \n" + movie.value!!.plot
        _shareableContent.postValue(content)
    }

}