package edu.temple.lab7__001

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity(), BookListFragment.BookSelectedInterface {
    private val viewModel: BookViewModel by viewModels()


    private lateinit var mainSearchButton: Button
    private val intentLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

            if (result.resultCode == Activity.RESULT_OK) {
                supportFragmentManager.commit {
                    replace(R.id.container1, BookListFragment())
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainSearchButton = findViewById(R.id.mainSearchButton)

        mainSearchButton.setOnClickListener {
            intentLauncher.launch(Intent(this, BookSearchActivity::class.java))
        }
        if (supportFragmentManager.findFragmentById(R.id.container1) is BookDetailsFragment) {
            supportFragmentManager.popBackStack()
        }


        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.container1, BookListFragment())
            }
        } else if (viewModel.getSelectedBook().value != null) {

            supportFragmentManager.commit {
                replace(R.id.container1, BookDetailsFragment())
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    override fun onBackPressed() {
        viewModel.setSelectedBook(null)
        super.onBackPressed()
    }
}