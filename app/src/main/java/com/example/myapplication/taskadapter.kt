package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class taskadapter(var task: MutableList<RegisterEntity>): RecyclerView.Adapter<taskadapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.show_task,parent,false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: taskadapter.MyViewHolder, position: Int) {
        val pos  = task[position]
       holder.title1.text = pos.title
        holder.description1.text = pos.description
    }
    override fun getItemCount(): Int {
        return task.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val title1 : TextView = itemView.findViewById(R.id.txttitlee)
    val description1 : TextView = itemView.findViewById(R.id.txtdesc)
    }
}