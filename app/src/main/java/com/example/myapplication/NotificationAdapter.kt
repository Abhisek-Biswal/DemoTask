package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotificationAdapter(val item: ArrayList<NotificationItem>): RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemName: TextView
        var itemPhone: TextView


        init {
            itemName = itemView.findViewById(R.id.tv_name)
            itemPhone = itemView.findViewById(R.id.tv_phone)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.rv_notification_contactlist,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val pos= item[position]

        holder.itemName.text = pos.name
        holder.itemPhone.text = pos.phone.toString()
    }

}