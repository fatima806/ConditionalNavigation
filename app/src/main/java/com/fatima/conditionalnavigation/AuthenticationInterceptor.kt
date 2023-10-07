package com.fatima.conditionalnavigation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination

class AuthenticationInterceptor(
    context: Context,
    private var navController: NavController
) : NavController.OnDestinationChangedListener {
    private lateinit var sharedPref: SharedPreferences

    init {
        sharedPref = context.getSharedPreferences("auth_pref", Context.MODE_PRIVATE)
    }


    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id == R.id.homeFragment) {
            val isAuthenticated = sharedPref.getBoolean("isAuthenticated", false)
            Log.e("TAG", "onDestinationChanged: $isAuthenticated", )
            if(!isAuthenticated){
                navController.navigate(R.id.loginFragment)
            }

        }
    }
}