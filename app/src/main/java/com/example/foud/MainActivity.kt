package com.example.foud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.foud.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController= findNavController(R.id.fragmentContainerView)

        var home: ImageView = findViewById(R.id.home_img)
        var fav: ImageView = findViewById(R.id.fav_img)
        var profile: ImageView = findViewById(R.id.profile_img)

        animate(home,true)


        home.setOnClickListener{navToHome(home,fav,profile)}
        fav.setOnClickListener{navToFav(home,fav,profile)}
        profile.setOnClickListener{navToProfile(home,fav,profile)}


    }

    private fun navToFav(home:ImageView,fav:ImageView,profile:ImageView) {
        animate(home,false)
        animate(fav, true)
        animate(profile, false)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_recipes"->navController.navigate(R.id.action_recipesFragment_to_favoritesFragment)
            "fragment_profile"->navController.navigate(R.id.action_profileFragment_to_favoritesFragment)
            else -> Log.d("hillo", "na_fav")
        }
    }

    private fun navToProfile(home:ImageView,fav:ImageView,profile:ImageView) {
        animate(home,false)
        animate(fav, false)
        animate(profile, true)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_recipes"->navController.navigate(R.id.action_recipesFragment_to_profileFragment)
            "fragment_favorites"->navController.navigate(R.id.action_favoritesFragment_to_profileFragment)
            else -> Log.d("hillo", "na_profile")
        }
    }

    private fun navToHome(home:ImageView,fav:ImageView,profile:ImageView) {
        animate(home,true)
        animate(fav, false)
        animate(profile, false)

        var page: String =  navController.currentDestination?.label.toString()

        when(page){
            "fragment_favorites"->navController.navigate(R.id.action_favoritesFragment_to_recipesFragment)
            "fragment_profile"->navController.navigate(R.id.action_profileFragment_to_recipesFragment)
            else -> Log.d("hillo", "na_home")
        }
    }


    private fun animate(img: ImageView, b: Boolean){
        if(b){
            val stateSet = intArrayOf(android.R.attr.state_checked)
            img.setImageState(stateSet, true)
        }else{
            val stateSet = intArrayOf(-android.R.attr.state_checked)
            img.setImageState(stateSet, true)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
