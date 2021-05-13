package br.com.lbrsilva.nasa.ui.carousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.data.model.Resource
import br.com.lbrsilva.nasa.data.repository.CarouselRepository
import br.com.lbrsilva.nasa.databinding.ActivityMainBinding
import br.com.lbrsilva.nasa.helper.extension.format
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class CarouselActivity : AppCompatActivity() {
//    private val viewModel: CarouselViewModel by viewModels()
    private lateinit var  viewModel: CarouselViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.setupUi()

        this.viewModel = CarouselViewModel(MockCarouselRepository(Resource.success(MockCarouselRepository.mockAscList())))

        this.viewModel.loadMedias(Date().format("yyyy-MM-dd"))
        this.viewModel.mediasLiveData.observe(this) {
            it?.let { list ->
                this.binding.carousel.adapter?.let { adapter ->
                    (adapter as CarouselAdapter).add(list)

                    if (list.size == 0) {
                        this.binding.carousel.removeLoadMore()
                    }
                } ?: run {
                    this.binding.carousel.adapter = CarouselAdapter(this.supportFragmentManager, it)
                }
            }
        }
    }

    private fun setupUi() {
        this.binding = ActivityMainBinding.inflate(this.layoutInflater)

        this.setContentView(this.binding.root)

        this.binding.carousel.addLoadMore {
            this.binding.carousel.adapter?.let { adapter ->
                (adapter as CarouselAdapter).list[adapter.count - 1].date?.let { date ->
                    this.viewModel.loadMedias(DateTransformer.calculateDays(date, -1))
                }
            }
        }
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
    }
}