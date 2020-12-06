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

class Fragment1 : Fragment() {

    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    private lateinit var toSecondButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("LOVELY TAG", "recreated")
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        toSecondButton = view.findViewById(R.id.btn_to_second)
        navigationView = view.findViewById(R.id.nav_view)

        toSecondButton.setOnClickListener {
            navController.navigate(R.id.action_fragment1_to_fragment2)
        }
        navigationView.setNavigationItemSelectedListener {
            navController.navigate(R.id.action_fragment1_to_aboutFragment)
            return@setNavigationItemSelectedListener true
        }
    }
}
