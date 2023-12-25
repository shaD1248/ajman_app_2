package ajman.shayan.joisty

import ajman.shayan.joisty.databases.JoistyDatabase
import ajman.shayan.joisty.view_models.JoistDesignViewModel
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

class JoistyApplication : Application() {
    lateinit var database: JoistyDatabase
    lateinit var repo: JoistDesignViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        database = JoistyDatabase.getDatabase(applicationContext)
        val dao = database.joistDesignDao()
        repo = JoistDesignViewModel(dao)
    }
}