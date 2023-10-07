package com.fatima.conditionalnavigation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fatima.conditionalnavigation.R
import com.google.android.material.button.MaterialButton

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = requireContext().getSharedPreferences("auth_pref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        view.findViewById<MaterialButton>(R.id.login).setOnClickListener {
            editor.putBoolean("isAuthenticated", true)
            editor.apply()
            editor.commit()
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

    }

}