package simra.androidtest.home.di

import simra.androidtest.home.domain.SearchMoviesUseCase
import simra.androidtest.home.viewmodel.HomeViewModel
import simra.androidtest.home.viewmodel.ImageDetailViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureHomeModule = module {
    factory { SearchMoviesUseCase(get()) }
    viewModel { HomeViewModel(get(),get()) }
    viewModel { ImageDetailViewModel() }
}