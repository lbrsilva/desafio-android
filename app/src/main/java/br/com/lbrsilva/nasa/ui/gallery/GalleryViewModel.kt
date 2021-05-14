package br.com.lbrsilva.nasa.ui.gallery

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lbrsilva.nasa.data.adapter.Resource.Status
import br.com.lbrsilva.nasa.data.model.Media
import br.com.lbrsilva.nasa.data.repository.GalleryRepository
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: GalleryRepository
) : ViewModel() {
    var mediasLiveData = MutableLiveData<ArrayList<Media>?>()
    var errorLiveData = MutableLiveData<String?>()

    fun loadMedias(endDate: String) {
        val startDate = DateTransformer.calculateDays(endDate, -10)

        viewModelScope.launch {
            val resource = repository.medias(startDate, endDate)

            if (resource.status == Status.SUCCESS) {
                val medias = (resource.data ?: listOf()).sortedByDescending {
                    it.date ?: ""
                }

                mediasLiveData.postValue(ArrayList(medias))
            } else {
                errorLiveData.postValue(resource.message)
            }
        }
    }
}