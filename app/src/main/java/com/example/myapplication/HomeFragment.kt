package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager.findFragment
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var newsAdapter : NewsAdapter
    lateinit var binding: FragmentHomeBinding
    lateinit var manager: RecyclerView.LayoutManager

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

            vModel.data(binding.tvHobby.text.toString())
        })
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }





}


