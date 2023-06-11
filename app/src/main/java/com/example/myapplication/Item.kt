package com.example.myapplication

data class Item(val name: String,
                val emailId: String,
                val phone: Int,
                val image: Int,
                var isSelected: Boolean = false)
