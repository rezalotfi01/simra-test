package simra.androidtest.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import simra.androidtest.common.utils.Event
import simra.androidtest.home.domain.SearchMoviesUseCase
import simra.androidtest.home.viewmodel.HomeMainViewModel
import simra.androidtest.model.Profile
import simra.androidtest.repository.PreferenceRepository
import simra.androidtest.repository.RxSchedulers
import io.mockk.*
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyString

@RunWith(JUnit4::class)
@SmallTest
class HomeMainUnitTests {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private var schedulers = RxSchedulers(Schedulers.trampoline(), Schedulers.trampoline())
    private var profileUseCase = mockk<SearchMoviesUseCase>()
    private var preferenceRepository = mockk<PreferenceRepository>()
    private lateinit var viewmodel: HomeMainViewModel
    private var profileObserver = mockk<Observer<Profile>>(relaxed = true)
    private var snackbarObserver = mockk<Observer<Event<String>>>(relaxed = true)
    private var error = Throwable()

    @Before
    fun setup(){
        every { profileUseCase(anyString(), anyBoolean()).result } returns Observable.just(Profile())
    }

    @Test
    fun `when fetch data state failed`(){
        every { preferenceRepository.getLoggedInUserId() } returns Maybe.error(error)

        viewmodel = HomeMainViewModel(profileUseCase, preferenceRepository, schedulers)
        viewmodel.fethcDataState()
        viewmodel.snackBarError.observeForever(snackbarObserver)

        verify {
            snackbarObserver.onChanged(viewmodel.snackBarError.value)
        }

        confirmVerified(snackbarObserver)
    }

    @Test
    fun `when user fetch profile data`(){
        every { preferenceRepository.getLoggedInUserId() } returns Maybe.just("xxx")
        every { profileUseCase("xxx", anyBoolean()).result } returns Observable.just(Profile())

        viewmodel = HomeMainViewModel(profileUseCase, preferenceRepository, schedulers)
        viewmodel.fethcDataState()
        viewmodel.profile.observeForever(profileObserver)

        verify {
            profileObserver.onChanged(Profile())
        }

        confirmVerified(profileObserver)
    }

    @Test
    fun `when user fetch profile data failed`(){
        every { preferenceRepository.getLoggedInUserId() } returns Maybe.just("xxx")
        every { profileUseCase("xxx", anyBoolean()).result } returns Observable.error(error)

        viewmodel = HomeMainViewModel(profileUseCase, preferenceRepository, schedulers)
        viewmodel.fethcDataState()
        viewmodel.snackBarError.observeForever(snackbarObserver)

        verify {
            snackbarObserver.onChanged(viewmodel.snackBarError.value)
        }

        confirmVerified(snackbarObserver)
    }

}