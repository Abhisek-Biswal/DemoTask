package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter( val productList :  MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private lateinit var onClickListener: OnClickListener


    var onItemClick : ((Product) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  ProductViewHolder {
        val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_of_products,parent,false)
        return ProductViewHolder(itemView)
    }

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {


         val productImg =  itemView.findViewById<ImageView>(R.id.imageView)

         val productTitle = itemView.findViewById<TextView>(R.id.textView)
         val productPrice = itemView.findViewById<TextView>(R.id.textView3)
         val productCategory = itemView.findViewById<TextView>(R.id.textView2)

    }

    fun setOnClickListener(onClickListener: ProductAdapter.OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {

        fun onClick(position: Int, productlist: MutableList<Product>)
    }


//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view =    layoutInflater.inflate(R.layout.item_of_products, parent, false)
//        return ProductViewHolder(view)
//    }
    override fun getItemCount() :Int{
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val pos = productList[position]
        holder.productTitle.text = pos.title
        holder.productPrice.text = "$"+pos.price.toString()
        holder.productCategory.text = pos.category
        holder.itemView.setOnClickListener {
            onClickListener.onClick(position,productList)
        }
        Picasso.get().load(pos.thumbnail).into(holder.productImg)
    }
}