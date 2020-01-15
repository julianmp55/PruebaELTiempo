package co.julian.eltiempo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nasa")
data class NasaEntity (val center: String?,
                       val date_created: String?,
                       val description: String?,
                       val keywords: String?,
                       val location: String?,
                       @PrimaryKey
                       val nasa_id: String,
                       val photographer: String?,
                       val title: String?,
                       val image: String?,
                       var is_favorite: Boolean)