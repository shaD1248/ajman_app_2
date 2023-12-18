package ajman.shayan.joisty.activities

import ajman.shayan.joisty.R
import ajman.shayan.joisty.fragments.JoistDesignFragment
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.AppBarConfiguration

class JoistDesignActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joist_design)
        setUpToolbar()

        val joistDesignFragment = JoistDesignFragment()
        passDataToFragment(joistDesignFragment)

        // Load JoistDesignFragment into JoistDesignActivity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, joistDesignFragment)
                .commit()
        }
    }

    private fun setUpToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = intent.getStringExtra("projectName")
        setSupportActionBar(toolbar)
        AppBarConfiguration(setOf(R.id.nav_joist_design))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun passDataToFragment(joistDesignFragment: JoistDesignFragment) {
        val joistDesignId = intent.getLongExtra("joistDesignId", 0)
        joistDesignFragment.arguments = Bundle().apply {
            putLong("joistDesignId", joistDesignId)
        }
    }
}
