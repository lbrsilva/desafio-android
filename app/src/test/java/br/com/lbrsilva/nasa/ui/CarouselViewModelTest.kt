package br.com.lbrsilva.nasa.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.lbrsilva.nasa.data.adapter.Resource
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.data.repository.CarouselRepository
import br.com.lbrsilva.nasa.rule.MainCoroutineRule
import br.com.lbrsilva.nasa.ui.carousel.CarouselViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class CarouselViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mediasLiveDataObserver: Observer<List<Media>?>

    @Mock
    private lateinit var errorLiveDataObserver: Observer<String?>

    private lateinit var viewModel: CarouselViewModel

    @Test
    fun `When viewModel get success then set MediasLiveData (Inverting ASC to DESC by date)`() {
        this.viewModel = CarouselViewModel(
            MockCarouselRepository(Resource.success(MockCarouselRepository.mockAscList()))
        )
        this.viewModel.mediasLiveData.observeForever(this.mediasLiveDataObserver)
        this.viewModel.loadMedias("2021-05-11")

        verify(this.mediasLiveDataObserver).onChanged(MockCarouselRepository.mockDescList())
    }

    @Test
    fun `When viewModel get error then set ErrorLiveData`() {
        val message = "Unknown Error"

        this.viewModel = CarouselViewModel(
            MockCarouselRepository(Resource.error(500, message))
        )
        this.viewModel.errorLiveData.observeForever(this.errorLiveDataObserver)
        this.viewModel.loadMedias("2021-05-11")

        verify(this.errorLiveDataObserver).onChanged(message)
    }
}

class MockCarouselRepository(private val resource: Resource<List<Media>>) : CarouselRepository {
    override suspend fun medias(startDate: String, endDate: String): Resource<List<Media>> {
        return resource
    }

    companion object {
        fun mockAscList(): List<Media> {
            return listOf(
                Media(
                    "Park Liu",
                    "2021-04-01",
                    "Testing 3",
                    "https://www.youtube.com/embed/B1R3dTdcpSU?rel=0",
                    "video",
                    "v1",
                    "Rocket Launch as Seen from the Space Station",
                    "https://www.youtube.com/embed/B1R3dTdcpSU?rel=0"
                ),
                Media(
                    "Eric Benson",
                    "2021-04-02",
                    "Testing 2",
                    "https://apod.nasa.gov/apod/image/2104/NGC3521-LRGB-1.jpg",
                    "image",
                    "v1",
                    "Ingenuity on Sol 39",
                    "https://apod.nasa.gov/apod/image/2104/NGC3521-LRGB-1.jpg"
                ),
                Media(
                    "Park Liu",
                    "2021-05-10",
                    "Testing 3",
                    "https://apod.nasa.gov/apod/image/2104/PIA24449.jpg",
                    "image",
                    "v1",
                    "Ingenuity on Sol 39",
                    "https://apod.nasa.gov/apod/image/2104/PIA24449_1024.jpg"
                )
            )
        }

        fun mockDescList(): List<Media> {
            return listOf(
                Media(
                    "Park Liu",
                    "2021-05-10",
                    "Testing 3",
                    "https://apod.nasa.gov/apod/image/2104/PIA24449.jpg",
                    "image",
                    "v1",
                    "Ingenuity on Sol 39",
                    "https://apod.nasa.gov/apod/image/2104/PIA24449_1024.jpg"
                ),
                Media(
                    "Eric Benson",
                    "2021-04-02",
                    "Testing 2",
                    "https://apod.nasa.gov/apod/image/2104/NGC3521-LRGB-1.jpg",
                    "image",
                    "v1",
                    "Ingenuity on Sol 39",
                    "https://apod.nasa.gov/apod/image/2104/NGC3521-LRGB-1.jpg"
                ),
                Media(
                    "Park Liu",
                    "2021-04-01",
                    "Testing 3",
                    "https://www.youtube.com/embed/B1R3dTdcpSU?rel=0",
                    "video",
                    "v1",
                    "Rocket Launch as Seen from the Space Station",
                    "https://www.youtube.com/embed/B1R3dTdcpSU?rel=0"
                )
            )
        }
    }
}