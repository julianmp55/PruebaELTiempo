package co.julian.eltiempo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.data.repository.NasaRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NasaViewMoldel internal constructor(val repository : NasaRepository): ViewModel(){



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


    fun getNasaData() {
        viewModelScope.launch {
            repository.getNasaData()
        }
    }

    fun getNasaDataLocal(): LiveData<List<NasaEntity?>> {
        return repository.getNasaDataLocal()
    }

    fun getFavoriteNasaDataLocal(): LiveData<List<NasaEntity?>> {
        return repository.getFavoriteNasaDataLocal()
    }

    fun deleteImage(image: NasaEntity) {
        viewModelScope.launch {
            repository.deleteImage(image)
        }
    }

    fun updateImage(image: NasaEntity) {
        viewModelScope.launch {
            repository.updateImage(image)
        }
    }

    fun getNasaImage(imageId: String) : LiveData<NasaEntity?> {

        return repository.getNasaImage(imageId)
    }
}
