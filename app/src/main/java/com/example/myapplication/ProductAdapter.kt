package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter (private val onProductClick: OnProductClick) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var productList =  emptyList <Product>()
    class ProductViewHolder(itemView : View, onProductClick : OnProductClick, productDataList : ArrayList<Product>) : RecyclerView.ViewHolder(itemView) {
        fun bind(holder: ProductViewHolder, position: Int, productList: ArrayList<Product>) {
            val productShow = productList[position]
            holder.productTitle.text = productShow.title
            holder.productPrice.text = "$"+productShow.price.toString()
            holder.productCategory.text = productShow.category
            Picasso.get().load(productShow.thumbnail).into(holder.productImg)
        }
        init {
            itemView.setOnClickListener {
                onProductClick.onClick(adapterPosition,productDataList)
            }
        }

        private val productImg =  itemView.findViewById<ImageView>(R.id.imageView)

        private val productTitle = itemView.findViewById<TextView>(R.id.textView)
        private val productPrice = itemView.findViewById<TextView>(R.id.textView3)
        private val productCategory = itemView.findViewById<TextView>(R.id.textView2)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =    layoutInflater.inflate(R.layout.item_of_products, parent, false)
        return ProductViewHolder(view,onProductClick, productList as ArrayList<Product>)
    }
    override fun getItemCount() = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(holder,position, productList as ArrayList<Product>)
    }

    fun setProductData(list : ArrayList<Product>){
        productList = list
    }

    interface OnProductClick{
        fun onClick(position: Int,productList: ArrayList<Product>)
    }
}