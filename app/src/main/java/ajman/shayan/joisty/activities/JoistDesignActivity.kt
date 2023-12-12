package ajman.shayan.joisty.activities

import ajman.shayan.joisty.R
import ajman.shayan.joisty.fragments.JoistDesignFragment
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class JoistDesignActivity : AppCompatActivity() {
    //    private lateinit var appBarConfiguration: AppBarConfiguration
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joist_design)

        val joistDesignFragment = JoistDesignFragment()
        passDataToFragment(joistDesignFragment)

        // Load JoistDesignFragment into JoistDesignActivity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, joistDesignFragment)
                .commit()
        }
    }

    private fun passDataToFragment(joistDesignFragment: JoistDesignFragment) {
        val joistDesignId = intent.getLongExtra("joistDesignId", 0)
        joistDesignFragment.arguments = Bundle().apply {
            putLong("joistDesignId", joistDesignId)
        }
    }
}
