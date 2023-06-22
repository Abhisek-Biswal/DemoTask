package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofritDemo : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var productsAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofrit_demo)

        val retrofit = Retrofit.Builder()
            .baseUrl(PRODUCT_LIST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productServices = retrofit.create(ProductApi::class.java)


        fetchProducts(productServices.getProducts())
    }

    private fun fetchProducts(listOfProduct: Call<ProductList>) {
        val call: Call<ProductList> = listOfProduct
        call.enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
//                binding.progressProducts.visibility = View.GONE
//                binding.recyclerProducts.visibility = View.VISIBLE


                showProducts(response.body()?.products)

            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                println(t)
            }

        })
    }

    private fun showProducts(productsList: ArrayList<Product>?) {
        if (productsList != null) {
            productsAdapter = ProductAdapter(productsList) {
                println(it.id)
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("productId", it.id)
                startActivity(intent)
            }
//            binding.recyclerProducts.adapter = productsAdapter
        } else {
            Toast.makeText(this, R.string.no_products, Toast.LENGTH_SHORT).show()
        }
    }


    companion object {
        const val PRODUCT_LIST_URL = "https://dummyjson.com/"
    }
}











