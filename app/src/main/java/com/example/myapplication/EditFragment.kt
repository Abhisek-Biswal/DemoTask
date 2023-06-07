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
import com.example.myapplication.databinding.ItemOfProductsBinding


class EditFragment : Fragment() {


    val view_Model by lazy {
        ViewModelProvider(requireActivity())[SharedViewModel::class.java]

    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = DataBindingUtil.inflate<FragmentEditBinding>(inflater,R.layout.fragment_edit,container,false)
        binding.saveButton.setOnClickListener{
            val transaction= requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_11,HomeFragment())
            transaction.commit()

            val name: String =binding.etvHome1.text.toString()
            val emaiId: String=binding.etvAddress1.text.toString()
            val address:String= binding.etvAddress1.toString()
            val phone: String= binding.etvPhone1.text.toString()
            val hobby: String= binding.etvHobby1.toString()

            view_Model.sendMessage(name,emaiId,address,phone,hobby)


        }
        view_Model.message.observe(viewLifecycleOwner){
            binding.etvHome1.setText(it.name)
            binding.etvEmail1.setText(it.emailId)
            binding.etvAddress1.setText(it.address)
            binding.etvPhone1.setText(it.phone)
            binding.etvHobby1.setText(it.hobby)

        }
        return binding.root

    }

}

