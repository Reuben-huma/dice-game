package eu.tutorials.dicegame

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
                view -> Snackbar.make(view, "Tap the dice to roll", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.results_item -> Toast.makeText(this, "Results", Toast.LENGTH_SHORT).show()
            R.id.share_item -> share()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun share() {
        val contextView = findViewById<View>(R.id.context_view)

        val shareIntent = ShareCompat.IntentBuilder.from(this)
            .setSubject(getString(R.string.share_subject))
            .setText(getString(R.string.share_text))
            .setType("text/plain")
            .intent

        try {
            startActivity(shareIntent)
        }
        catch (ex: Exception) {
            Snackbar.make(contextView, "Error, Please try again!", Snackbar.LENGTH_SHORT).show()
        }
    }
}