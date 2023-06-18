package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemOfProductsBinding

class ProductAdapter( val productList :  MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var onClickListener: OnClickListener




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ProductViewHolder {
//        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_products,parent,false)
//        return ProductViewHolder(itemView)
        val itemView= LayoutInflater.from(parent.context)
        val binding= DataBindingUtil.inflate<ItemOfProductsBinding>(itemView,R.layout.item_of_products,parent,false)
        return ProductViewHolder (binding)
    }
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//
//        holder.bind(productList[position])
////        val pos = productList[position]
////        holder.productTitle.text = pos.title
////        holder.productPrice.text = "$"+pos.price.toString()
////        holder.productCategory.text = pos.category
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(position,productList)
//        }
//        Picasso.get().load(pos.thumbnail).into(holder.productImg)
//    }

    override fun getItemCount() :Int{
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        holder.bind(productList[position])
//        val pos = productList[position]
//        holder.productTitle.text = pos.title
//        holder.productPrice.text = "$"+pos.price.toString()
//        holder.productCategory.text = pos.category
//        holder.itemView.setOnClickListener {
//            onClickListener.onClick(position,productList)
//        }
//        Picasso.get().load(pos.thumbnail).into(holder.productImg)
    }
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, productlist: MutableList<Product>)
    }
//    class ProductViewHolder(val binding: ItemOfProductsBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(product: Product) {
//
//
//            val productImg = itemView.findViewById<ImageView>(R.id.imageView)
//
//            val productTitle = binding.textView.text = product.title
//            val productPrice = binding.textView2.text = product.price
//            val productCategory = binding.textView3.text = product.category
//        }
//    }

    class ProductViewHolder(val binding: ItemOfProductsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {


            val productImg = itemView.findViewById<ImageView>(R.id.imageView)

            binding.textView.text = product.title
            binding.textView2.text = product.price.toString()
            binding.textView3.text = product.category
        }
    }






}




//         val productTitle = itemView.findViewById<TextView>(R.id.textView)
//         val productPrice = itemView.findViewById<TextView>(R.id.textView3)
//         val productCategory = itemView.findViewById<TextView>(R.id.textView2)
















