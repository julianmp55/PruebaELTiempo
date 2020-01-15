package co.julian.eltiempo.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.data.local.dao.NasaDao
import co.julian.eltiempo.data.remote.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NasaRepository (val dao : NasaDao){



    suspend fun getNasaData() {

        val nasaList = mutableListOf<NasaEntity>()

        val response = ApiClient.build()?.loadData("apollo 2011")!!
        withContext(Dispatchers.IO){

            response.body()?.collection?.items?.forEach {
                nasaList.add(
                    NasaEntity(
                        it?.data?.get(0)?.center,
                        it?.data?.get(0)?.date_created,
                        it?.data?.get(0)?.description,
                        it?.data?.get(0)?.keywords?.get(0),
                        it?.data?.get(0)?.location,
                        it?.data?.get(0)?.nasa_id!!,
                        it?.data?.get(0)?.photographer,
                        it?.data?.get(0)?.title,
                        it?.links?.get(0)?.href,
                        false
                    )
                )



            }


            dao.insertNasaList(nasaList)


        }
    }

    fun getNasaDataLocal(): LiveData<List<NasaEntity?>> {
        return dao.getNasaDataLocal()
    }

    fun getFavoriteNasaDataLocal(): LiveData<List<NasaEntity?>> {
        return dao.getFavoriteNasaDataLocal()
    }

    suspend fun deleteImage(image: NasaEntity) {
        withContext(Dispatchers.IO){
            dao.deleteImage(image)
        }
    }

    suspend fun updateImage(image: NasaEntity) {
        withContext(Dispatchers.IO){
            dao.updateImage(image)
        }
    }

    fun getNasaImage(imageId: String): LiveData<NasaEntity?> {

        return dao.getNasaImage(imageId)

    }


    companion object {
        @Volatile private var instance: NasaRepository? = null
        fun getInstance(nasaDao: NasaDao) =
            instance ?: synchronized(this) {
                instance
                    ?: NasaRepository(nasaDao).also { instance = it }
            }
    }




}
