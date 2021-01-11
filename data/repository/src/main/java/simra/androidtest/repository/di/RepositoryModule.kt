package simra.androidtest.repository.di

import org.koin.dsl.module
import simra.androidtest.repository.AppDispatchers
import simra.androidtest.repository.MovieRepository
import simra.androidtest.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { MovieRepositoryImpl(get(), get()) as MovieRepository }
}