package simra.androidtest.rezalotfi.di

import simra.androidtest.features.moviedetail.di.featureMovieDetailModule
import simra.androidtest.home.di.featureHomeModule
import simra.androidtest.local.di.localModule
import simra.androidtest.rezalotfi.BuildConfig
import simra.androidtest.remote.di.remoteModule
import simra.androidtest.repository.di.repositoryModule
import org.koin.core.module.Module

/**
 *  List all of module used by root app here
 */

val appComponent :List<Module> = listOf(
    remoteModule(BuildConfig.BASE_URL),
    localModule,
    repositoryModule,
    featureHomeModule,
    featureMovieDetailModule
)