package ajman.shayan.joisty.activities

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.R
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.view_models.JoistDesignViewModel
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var toggle: ActionBarDrawerToggle? = null
    private var deleteAction: MenuItem? = null
    private lateinit var repo: JoistDesignViewModel
    var recyclerView: RecyclerView? = null
    var joistDesigns = mutableListOf<JoistDesign>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setUpLocale()
        setContentView(R.layout.activity_main)
        setUpToolbar()
    }

    private fun setUpLocale() {
        val locale = Locale("fa")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun setUpToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        this.toggle = toggle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeAsUpIndicator(androidx.appcompat.graphics.drawable.DrawerArrowDrawable(applicationContext))
    }

    override fun onResume() {
        super.onResume()
        val application = application as JoistyApplication
        repo = application.repo
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                if (menu.findItem(R.id.action_delete) == null) {
                    menuInflater.inflate(R.menu.main, menu)
                }
                deleteAction = menu.findItem(R.id.action_delete)
                updateDeleteActionVisibility()
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                var changed = false
                joistDesigns.removeIf {
                    changed = changed or it.selected
                    if (it.selected) repo.delete(it)
                    it.selected
                }
                if (changed) {
                    updateDeleteActionVisibility()
                    recyclerView?.adapter?.notifyDataSetChanged()
                }
                return true
            }
        }, this, Lifecycle.State.RESUMED)
        return true
    }

    fun updateDeleteActionVisibility() {
        deleteAction?.isVisible = joistDesigns.count { it.selected } > 0
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return toggle?.onOptionsItemSelected(item) == true || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
