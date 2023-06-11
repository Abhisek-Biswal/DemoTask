package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val items: MutableList<Item>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemImage: ImageView
        var itemName: TextView
        var itemPhone: TextView
        var itemEmail: TextView
        var itemCheckBox: CheckBox
        var itemImageBtn : ImageButton


        init {

            itemImage= itemView.findViewById(R.id.iv_of_rv)
            itemName = itemView.findViewById(R.id.tv_name)
            itemEmail = itemView.findViewById(R.id.tv_email)
            itemPhone = itemView.findViewById(R.id.tv_number)
            itemImageBtn = itemView.findViewById(R.id.delete_btn)
            itemCheckBox = itemView.findViewById(R.id.checkbox)

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v= LayoutInflater.from(parent.context).inflate(R.layout.rv_row,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemImage.setImageResource(items[position].image)
        holder.itemName.text = items[position].name
        holder.itemPhone.text = items[position].phone.toString()
        holder.itemEmail.text = items[position].emailId
        holder.itemCheckBox.isChecked = items[position].isSelected
        holder.itemCheckBox.setOnClickListener {
            items[position].isSelected = !items[position].isSelected
            notifyItemChanged(position)
        }
        holder.itemImageBtn.setOnClickListener {
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,items.size)
            items.removeAt(position)
        }
    }
}