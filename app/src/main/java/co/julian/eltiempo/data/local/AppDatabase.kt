package co.julian.eltiempo.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.julian.eltiempo.data.entity.NasaEntity
import co.julian.eltiempo.data.remote.model.Link
import co.julian.eltiempo.data.local.dao.NasaDao


@Database(entities = [NasaEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){


    abstract fun nasaDao(): NasaDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "nasa-db")

                .fallbackToDestructiveMigration()
                .build()
        }
    }

}
