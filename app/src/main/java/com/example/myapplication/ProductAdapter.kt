package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter( val productList :  MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


         val productImg =  itemView.findViewById<ImageView>(R.id.imageView)

         val productTitle = itemView.findViewById<TextView>(R.id.textView)
         val productPrice = itemView.findViewById<TextView>(R.id.textView3)
         val productCategory = itemView.findViewById<TextView>(R.id.textView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =    layoutInflater.inflate(R.layout.item_of_products, parent, false)
        return ProductViewHolder(view)
    }
    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val position = productList[position]
        holder.productTitle.text = position.title
        holder.productPrice.text = "$"+position.price.toString()
        holder.productCategory.text = position.category


        Picasso.get().load(position.thumbnail).into(holder.productImg)
    }
}