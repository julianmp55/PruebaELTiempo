package co.julian.eltiempo.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import co.julian.eltiempo.data.entity.NasaEntity

@Dao
interface NasaDao {


    @Query("SELECT * FROM nasa")
    fun getNasaDataLocal(): LiveData<List<NasaEntity?>>

    @Query("SELECT * FROM nasa WHERE is_favorite = 1")
    fun getFavoriteNasaDataLocal(): LiveData<List<NasaEntity?>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNasaList(it: List<NasaEntity>)

    @Delete
    fun deleteImage(image: NasaEntity)

    @Update
    fun updateImage(image: NasaEntity)




    @Query("SELECT * FROM nasa WHERE nasa_id =:imageId")
    fun getNasaImage(imageId: String): LiveData<NasaEntity?>

}
