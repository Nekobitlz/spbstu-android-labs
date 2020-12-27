package ru.spbstu.lab3_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Fragment2 : Fragment() {

    private lateinit var toFirstButton: Button
    private lateinit var toThirdButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toFirstButton = view.findViewById(R.id.btn_to_first)
        toThirdButton = view.findViewById(R.id.btn_to_third)

        toFirstButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment1)
        }
        toThirdButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment2_to_fragment3)
        }
    }
}