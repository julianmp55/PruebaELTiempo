package co.julian.eltiempo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.julian.eltiempo.data.repository.NasaRepository

class NasaViewModelFactory (private val repository: NasaRepository): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = NasaViewMoldel(repository) as T

}
