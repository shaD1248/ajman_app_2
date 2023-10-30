package ajman.shayan.joisty.databases

import ajman.shayan.joisty.dao.JoistDesignDao
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.type_converters.EnumConverter
import ajman.shayan.joisty.type_converters.LocalDateTimeConverter
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [JoistDesign::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class, EnumConverter::class)
abstract class JoistyDatabase: RoomDatabase() {
    abstract fun joistDesignDao(): JoistDesignDao
    companion object {
        @Volatile
        private var INSTANCE: JoistyDatabase? = null

        fun getDatabase(context: Context): JoistyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JoistyDatabase::class.java,
                    "joisty-database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}