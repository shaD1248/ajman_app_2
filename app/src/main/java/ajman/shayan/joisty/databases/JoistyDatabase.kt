package ajman.shayan.joisty.databases

import ajman.shayan.joisty.dao.JoistDesignDao
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.migrations.Migration2To1
import ajman.shayan.joisty.migrations.Migration2To3
import ajman.shayan.joisty.migrations.Migration3To2
import ajman.shayan.joisty.migrations.Migration4To3
import ajman.shayan.joisty.type_converters.EnumConverter
import ajman.shayan.joisty.type_converters.LocalDateTimeConverter
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.DeleteTable
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.AutoMigrationSpec

@RequiresApi(Build.VERSION_CODES.O)
@Database(
    entities = [JoistDesign::class],
    version = 1,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 3, to = 4, spec = JoistyDatabase.Migration3To4Spec::class),
    ],
)
@TypeConverters(LocalDateTimeConverter::class, EnumConverter::class)
abstract class JoistyDatabase : RoomDatabase() {
    abstract fun joistDesignDao(): JoistDesignDao

    companion object {
        @Volatile
        private var INSTANCE: JoistyDatabase? = null

        fun getDatabase(context: Context): JoistyDatabase {
            return INSTANCE ?: synchronized(this) {
                val databaseBuilder = Room.databaseBuilder(
                    context.applicationContext,
                    JoistyDatabase::class.java,
                    "joisty-database"
                )
                databaseBuilder.addMigrations(
                    Migration2To1(),
                    Migration2To3(),
                    Migration3To2(),
                    Migration4To3(),
                )
                val instance = databaseBuilder.build()
                INSTANCE = instance
                instance
            }
        }
    }

    @RenameColumn(tableName = "loading", fromColumnName = "id", toColumnName = "loading_id")
    class Migration3To4Spec : AutoMigrationSpec
}