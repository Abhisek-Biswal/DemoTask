package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProductDetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        lateinit var productData : Product

        if(intent.extras != null){
            productData  = intent.getSerializableExtra("details") as Product
        }

        findViewById<TextView>(R.id.product_detail_title_txtvw).text = productData.title
        findViewById<TextView>(R.id.description_txtvw).text = "Description : \n "+productData.description
        findViewById<TextView>(R.id.price_txtvw).text = "Price : $"+productData.price.toString()
        findViewById<TextView>(R.id.discountpercentage_txtvw).text = "Discount "+productData.discountPercentage.toString()+"%"
        findViewById<TextView>(R.id.stock_txtvw).text = "Stock : "+productData.stock.toString()
        findViewById<TextView>(R.id.brand_txtvw).text = "Company Name "+productData.brand



    }

}