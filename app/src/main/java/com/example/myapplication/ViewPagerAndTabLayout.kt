package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class ViewPagerAndTabLayout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager_and_tab_layout)

        val viewPager=findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter= ViewPagerAndTabLayoutAdapter(supportFragmentManager)

        val tabLayout=findViewById<TabLayout>(R.id.tablayout)
        tabLayout.setupWithViewPager(viewPager)

    }
}