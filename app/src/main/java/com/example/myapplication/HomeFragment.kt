package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentEditBinding
import com.example.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    val vModel by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.editButton.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_11, EditFragment())
            transaction.commit()



        }
        vModel.message.observe(viewLifecycleOwner, Observer<Home> {
            binding.tvHome.setText(it.name)
            binding.tvEmail.setText(it.emailId)
            binding.tvAddress.setText(it.address)
            binding.tvPhone.setText(it.phone)
            binding.tvHobby.setText(it.hobby)
        })
        return binding.root
    }




}


