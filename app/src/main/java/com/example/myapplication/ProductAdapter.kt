package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemOfProductsBinding
import com.squareup.picasso.Picasso

class ProductAdapter( val productList :  MutableList<Product>, val clickListener: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ProductViewHolder {

        val itemView= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<ItemOfProductsBinding>(itemView,R.layout.item_of_products,parent,false)
        return ProductViewHolder (binding){
            clickListener(productList[it])
        }
    }

    override fun getItemCount() :Int{
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.binding.productItem = productList[position]

    }
    class ProductViewHolder(val binding: ItemOfProductsBinding,clickAtPosition : (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                clickAtPosition(adapterPosition)
            }
        }
    }
}
@BindingAdapter("imgUrl")
fun ImageView.loadImg(url: String) {
    Picasso.get().load(url).placeholder(R.drawable.ic_baseline_image).into(this)
}





















