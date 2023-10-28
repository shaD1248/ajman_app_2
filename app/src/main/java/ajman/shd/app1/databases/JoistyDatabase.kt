package ajman.shd.app1.databases

import ajman.shd.app1.dao.JoistDesignDao
import ajman.shd.app1.entities.JoistDesign
import ajman.shd.app1.type_converters.EnumConverter
import ajman.shd.app1.type_converters.LocalDateTimeConverter
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