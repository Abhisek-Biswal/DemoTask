package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddTextFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val transaction =  inflater.inflate(R.layout.fragment_add_text, container, false)
        val goBackBtn = transaction.findViewById<Button>(R.id.backFragment)
        val etName = transaction.findViewById<EditText>(R.id.etName)

        goBackBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("message",etName.text.toString())
            val fragment2 = HomFrag()
            fragment2.arguments = bundle
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout,fragment2).addToBackStack("null").commit()


        }


        return transaction

    }



}