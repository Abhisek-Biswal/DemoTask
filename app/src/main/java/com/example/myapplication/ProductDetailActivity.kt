package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.databinding.ActivityProductDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var viewPagerAdapter: ImgAdapter

    lateinit var binding: ActivityProductDetailBinding
    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_product_detail)


        viewPager = findViewById(R.id.viewpagerproimages)
//        setContentView(R.layout.activity_product_detail)

        val productId = intent.getIntExtra("productId", 0)

        val retroFit = Retrofit.Builder().baseUrl(RetrofritDemo.PRODUCT_LIST_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val productsDetailServices = retroFit.create(ProductApi::class.java)

        fetchDataFromUrl(productsDetailServices.getOneProduct(productId))
    }
    private fun fetchDataFromUrl(oneProductsDetail: Call<Product>) {
        val call: Call<Product> = oneProductsDetail
        call.enqueue(object : Callback<Product> {
            override fun onResponse(
                call: Call<Product>,
                response: Response<Product>
            ) {
                println("Connecting...")
                binding.detailsProgress.visibility = View.GONE
                showProductDetails(response.body())
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                println("Failed....")
            }

        })

    }
    @SuppressLint("StringFormatInvalid")
    private fun showProductDetails(body: Product?) {

        //Using viewBinding to find TextView
        binding.tvprotitle.text = body?.title
        binding.tvprodescription.text = getString(R.string.description, body?.description)
        binding.tvproprice.text = getString(R.string.dollar_sign, body?.price)
        binding.tvprodiscount.text =
            getString(R.string.discount, body?.discountPercentage.toString())
        binding.tvprostock.text = getString(R.string.stock, body?.stock)
        binding.tvprobrand.text = getString(R.string.brandName, body?.brand)

        viewPagerAdapter = ImgAdapter(this, body!!.images)
        viewPager.adapter = viewPagerAdapter

//        binding.viewpagerproimages.setupWithViewPager(viewPager, true)
    }

}