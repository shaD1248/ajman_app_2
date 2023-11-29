package ajman.shayan.joisty.activities

import ajman.shayan.joisty.JoistyApplication
import ajman.shayan.joisty.R
import ajman.shayan.joisty.adapters.JoistDesignAdapter
import ajman.shayan.joisty.adapters.JoistDesignViewHolder
import ajman.shayan.joisty.entities.JoistDesign
import ajman.shayan.joisty.models.JoistDesignParcelable
import ajman.shayan.joisty.view_models.JoistDesignViewModel
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var repo: JoistDesignViewModel
    private var recyclerView: RecyclerView? = null
    private var deleteAction: MenuItem? = null
    private var toggle: ActionBarDrawerToggle? = null

    var joistDesigns = mutableListOf(
        JoistDesign(1000.0),
        JoistDesign(600.0),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setUpLocale()
        setContentView(R.layout.activity_main)
        setUpDrawer()
        setJoistDesignAdapter()
        addListenerForCreateButton()
    }

    private fun setUpLocale() {
        val locale = Locale("fa")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        resources.updateConfiguration(config, resources.displayMetrics)
    }

    private fun setUpDrawer() {
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
        return if (toggle?.onOptionsItemSelected(item) == true) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setJoistDesignAdapter() {
        val application = this.application as JoistyApplication
        repo = application.repo
//        joistDesigns = viewModel.allJoistDesigns.toMutableList()

        val updateRecyclerView = {joistDesigns: MutableList<JoistDesign> ->
            this.joistDesigns = joistDesigns
            recyclerView = findViewById(R.id.recyclerView)
            recyclerView?.layoutManager = LinearLayoutManager(this)
            recyclerView?.adapter =
                JoistDesignAdapter(joistDesigns, getOnItemClick(), getOnItemLongClick())
        }

        repo.loadAllJoists(updateRecyclerView)
    }

    private fun getOnItemClick() = { joistDesign: JoistDesign, holder: JoistDesignViewHolder ->
        if (joistDesign.selected) {
            joistDesign.selected = false
            onItemSelectionChanged(joistDesign, holder)
        } else {
            loadJoistDesignActivity(joistDesign)
        }
    }

    private fun loadJoistDesignActivity(joistDesign: JoistDesign) {
        val intent = Intent(this, JoistDesignActivity::class.java)
        intent.putExtra("joistDesignParcelable", JoistDesignParcelable(joistDesign))
        startActivity(intent)
    }

    private fun getOnItemLongClick() = { joistDesign: JoistDesign, holder: JoistDesignViewHolder ->
        if (!joistDesign.selected) {
            joistDesign.selected = true
            onItemSelectionChanged(joistDesign, holder)
        }
    }

    private fun onItemSelectionChanged(joistDesign: JoistDesign, holder: JoistDesignViewHolder) {
        holder.checkIcon.visibility = if (joistDesign.selected) View.VISIBLE else View.GONE
        updateDeleteActionVisibility()
    }

    private fun addListenerForCreateButton() {
        findViewById<FloatingActionButton?>(R.id.fabAddJoist).setOnClickListener {
            val joistDesign = JoistDesign(600.0)
            joistDesign.projectName = getString(R.string.default_project_name)
            joistDesigns.add(0, joistDesign)
            loadJoistDesignActivity(joistDesign)
            recyclerView?.adapter?.notifyDataSetChanged()
        }
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

    private fun updateDeleteActionVisibility() {
        deleteAction?.isVisible = joistDesigns.count { it.selected } > 0
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
