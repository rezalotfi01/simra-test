package simra.androidtest.home.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import simra.androidtest.model.Movie
import simra.androidtest.repository.MovieRepository
import simra.androidtest.repository.utils.Resource

class SearchMoviesUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(shouldFetch: Boolean = true,
                                title: String,
                                page: String,
                                type: String = "movie"): LiveData<Resource<MutableList<Movie>>> {
        return Transformations.map(repository.searchMovies(shouldFetch, title, page, type)) {
            it // TODO: Place specific logic actions
        }
    }
}