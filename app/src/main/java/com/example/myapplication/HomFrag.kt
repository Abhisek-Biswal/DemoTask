package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment


class HomFrag : Fragment(),CommunicateBetweenTwoFragment {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val data = activity as CommunicateBetweenTwoFragment

        val view = inflater.inflate(R.layout.fragment_hom, container, false)
        val nextPageBtn  = view.findViewById<Button>(R.id.btn)
        val frgTitle = view.findViewById<TextView>(R.id.edittext)
        nextPageBtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.frame_layout,AddTextFragment()).addToBackStack("null").commit()
        }
        frgTitle.text = arguments?.getString("message")

        val popMsg = view.findViewById<Button>(R.id.toast_frag)
        popMsg.setOnClickListener {
                data.sendText(frgTitle.text.toString())
        }
        return view
    }

    override fun sendText(msg: String) {
    }





}