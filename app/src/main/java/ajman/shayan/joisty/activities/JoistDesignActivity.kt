package ajman.shayan.joisty.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ajman.shayan.joisty.R
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.fragments.JoistDesignFragment
import ajman.shayan.joisty.models.JoistDesignParcelable
import android.os.Build
import androidx.annotation.RequiresApi

class JoistDesignActivity : AppCompatActivity() {
//    private lateinit var appBarConfiguration: AppBarConfiguration
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joist_design)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        setSupportActionBar(toolbar)
//        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        val joistDesignParcelable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val joistDesign = intent.extras?.get("joistDesignParcelable") as JoistDesign?
            if (joistDesign != null) JoistDesignParcelable(joistDesign) else null
        } else {
            intent.extras?.get("joistDesignParcelable")
        } as JoistDesignParcelable?
        val joistDesignFragment = JoistDesignFragment()
        if (joistDesignParcelable != null) {
            joistDesignFragment.arguments = Bundle().apply {
                putParcelable("joistDesignParcelable", joistDesignParcelable)
            }
        }

        // Load JoistDesignFragment into JoistDesignActivity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, joistDesignFragment)
                .commit()
        }
    }
}
