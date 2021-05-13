package br.com.lbrsilva.nasa.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lbrsilva.nasa.data.model.Picture
import br.com.lbrsilva.nasa.data.model.Resource.Status
import br.com.lbrsilva.nasa.data.repository.CarouselRepository
import br.com.lbrsilva.nasa.helper.transformer.DateTransformer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarouselViewModel @Inject constructor(
    private val repository: CarouselRepository
) : ViewModel() {
    var picturesLiveData = MutableLiveData<ArrayList<Picture>?>()
    var errorLiveData = MutableLiveData<String?>()

    fun loadPictures(endDate: String) {
        val startDate = DateTransformer.subtractDays(endDate)

        viewModelScope.launch {
            val resource = repository.pictures(startDate, endDate)

            if (resource.status == Status.SUCCESS) {
                val pictures = (resource.data ?: listOf()).sortedByDescending {
                    it.date ?: ""
                }

                picturesLiveData.postValue(ArrayList(pictures))
            } else {
                errorLiveData.postValue(resource.message)
            }
        }
    }
}