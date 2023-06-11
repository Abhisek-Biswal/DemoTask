package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)


        val recyclerView= findViewById<RecyclerView>(R.id.my_rv)

        val itemObject = mutableListOf<Item>()

        itemObject.add(Item("Abhisek","abhisek@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Abhi","abhi@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Ankit","ankit@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Amarjit","amarjit@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Aman","aman@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Abhinash","abhinash@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Abhinav","abhinav@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Abhaya","abhaya@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Aditya","aditya@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Akash","akash@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Akshay","akshay@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Anil","anil@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Amitabh","amitabh@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Arbaz","arbaz@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Ashutosh","ashutosh@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Arvind","arvind@gmail.com",123,R.drawable.ic_image))
        itemObject.add(Item("Amit","amit@gmail.com",123,R.drawable.ic_image))




        recyclerView.layoutManager = LinearLayoutManager(this)

        val deleteBtn: ImageButton = findViewById(R.id.del_btn)
        val recyclerViewAdapter = RecyclerViewAdapter(itemObject)
        recyclerView.adapter= RecyclerViewAdapter(itemObject)

        deleteBtn.setOnClickListener{
            val newItemList = itemObject.filter { it.isSelected }

            for(i in newItemList){

                itemObject.remove(i)
                recyclerViewAdapter.notifyItemRangeChanged(itemObject.indexOf(i),itemObject.size)
                recyclerViewAdapter.notifyItemRemoved(itemObject.indexOf(i))
            }
        }
    }
}