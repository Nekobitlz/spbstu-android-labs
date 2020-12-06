package ru.spbstu.lab3_2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView

class Fragment2 : Fragment() {

    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var toFirstButton: Button
    private lateinit var toThirdButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        toFirstButton = view.findViewById(R.id.btn_to_first)
        toThirdButton = view.findViewById(R.id.btn_to_third)
        navigationView = view.findViewById(R.id.nav_view)

        toFirstButton.setOnClickListener {
            navController.navigate(R.id.action_fragment2_to_fragment1)
        }
        toThirdButton.setOnClickListener {
            navController.navigate(R.id.action_fragment2_to_fragment3)
        }
        navigationView.setNavigationItemSelectedListener {
            navController.navigate(R.id.action_fragment2_to_aboutFragment)
            return@setNavigationItemSelectedListener true
        }
    }
}