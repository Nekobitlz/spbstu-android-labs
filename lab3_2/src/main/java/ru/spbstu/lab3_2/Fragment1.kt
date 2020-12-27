package ru.spbstu.lab3_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class Fragment1 : Fragment() {

    private lateinit var toSecondButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.activity_1, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        toSecondButton = view.findViewById(R.id.btn_to_second)
        toSecondButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragment1_to_fragment2)
        }
    }
}
