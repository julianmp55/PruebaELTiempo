package co.julian.eltiempo.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import co.julian.eltiempo.data.local.AppDatabase
import co.julian.eltiempo.data.repository.NasaRepository
import co.julian.eltiempo.viewmodel.NasaViewModelFactory

object InjectorUtils {
    fun provideNasaViewModelFactory(context: Context): NasaViewModelFactory {

        return NasaViewModelFactory(NasaRepository.getInstance(AppDatabase.getInstance(context.applicationContext).nasaDao()))

    }

}
