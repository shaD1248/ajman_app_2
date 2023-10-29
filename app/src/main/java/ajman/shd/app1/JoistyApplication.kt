package ajman.shd.app1

import ajman.shd.app1.databases.JoistyDatabase
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room

class JoistyApplication : Application() {
    lateinit var database: JoistyDatabase
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        val databaseBuilder = Room.databaseBuilder(
            applicationContext,
            JoistyDatabase::class.java,
            "joist-database"
        )
        database = databaseBuilder.build()
    }
}