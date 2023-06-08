package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAndTabLayoutAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                Fragment1()
            }
            1 ->{
                Fragment2()
            }
            2 ->{
                Fragment3()
            }
            else ->{
                Fragment1()
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 ->{
                return "Chats"
            }
            1 ->{
                return "Status"
            }
            2 ->{
                return "Calls"
            }
        }
        return super.getPageTitle(position)
    }
}