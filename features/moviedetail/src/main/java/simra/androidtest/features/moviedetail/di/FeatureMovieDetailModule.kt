package simra.androidtest.features.moviedetail.di

import simra.androidtest.features.moviedetail.domain.GetMovieUseCase
import simra.androidtest.features.moviedetail.viewmodel.MovieDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureMovieDetailModule = module {
    factory { GetMovieUseCase(get()) }
    viewModel { MovieDetailViewModel(get(),get()) }
}