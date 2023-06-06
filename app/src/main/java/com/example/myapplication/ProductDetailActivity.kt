package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityProductDetailBinding
import com.squareup.picasso.Picasso

class ProductDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductDetailBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_product_detail)


//        setContentView(R.layout.activity_product_detail)



        if(intent.extras != null){
            val productDetail = intent.getSerializableExtra("abc") as Product

            binding.tvprotitle.text=productDetail.title
            binding.tvprodiscount.text=productDetail.discountPercentage.toString()
            binding.tvprostock.text= productDetail.stock.toString()
            binding.tvprodescription.text=  productDetail.description
            binding.tvproprice.text=productDetail.price.toString()
            binding.tvprorating.text=productDetail.rating.toString()
            binding.tvprobrand.text= productDetail.brand
            binding.tvprocategory.text= productDetail.category
            val imageproduct = findViewById<ImageView>(R.id.imgv1)
//            val title = findViewById<TextView>(R.id.tvprotitle)
//            val discount = findViewById<TextView>(R.id.tvprodiscount)
//            val stock = findViewById<TextView>(R.id.tvprostock)
//            val decrp = findViewById<TextView>(R.id.tvprodescription)
//            val price = findViewById<TextView>(R.id.tvproprice)
//            val rating = findViewById<TextView>(R.id.tvprorating)
//            val brand = findViewById<TextView>(R.id.tvprobrand)
//            val category = findViewById<TextView>(R.id.tvprocategory)
            val viewpagerimg = findViewById<ViewPager>(R.id.viewpagerproimages)


//            title.text = productDetail.title
//            discount.text = productDetail.discountPercentage.toString()
//            stock.text = productDetail.stock.toString()
//            decrp.text = productDetail.description
//            price.text = productDetail.price.toString()
//            rating.text = productDetail.rating.toString()
//            brand.text = productDetail.brand
//            category.text = productDetail.category
            val  mypagerAdapter = ImgAdapter(this,productDetail.images)
            viewpagerimg.adapter = mypagerAdapter
            Picasso.get().load(productDetail.thumbnail).into(imageproduct)
        }



    }

}