package simra.androidtest.features.moviedetail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import simra.androidtest.model.Movie
import simra.androidtest.repository.MovieRepository
import simra.androidtest.repository.utils.Resource

class GetMovieUseCase(private val repository: MovieRepository) {

    suspend operator fun invoke(id: String): LiveData<Resource<Movie>> {
        return Transformations.map(repository.getMovie(true, id)) {
            it // Place here your specific logic actions
        }
    }
}