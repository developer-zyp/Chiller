package com.example.chiller

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.chiller.common.AppConstant
import com.example.chiller.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        setupApiURl()
        setupBottomNav()

    }

    private fun setupApiURl() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ApiURL")
        val input = EditText(this)
        input.setText(AppConstant.BASE_RES_URL)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK",
            DialogInterface.OnClickListener { dialog, which ->
                AppConstant.BASE_RES_URL = input.text.toString()
            })
        builder.setNegativeButton("Cancel",
            DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })

        builder.show()
    }

    private fun setupBottomNav() {
        navController = Navigation.findNavController(this, R.id.nav_host)

        binding.apply {

            bottomNavigationView.setupWithNavController(navController)

            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                bottomNavigationView.visibility = when (destination.id) {
                    R.id.barDetailFragment -> View.GONE
                    else -> View.VISIBLE

                }
            }

        }

//        NavigationUI.setupActionBarWithNavController(this, navController)

    }

}