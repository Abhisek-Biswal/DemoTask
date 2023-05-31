package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class Retrofrit_Demo : AppCompatActivity(),ProductAdapter.OnProductClick {

    lateinit var recyclerView: RecyclerView
    lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofrit_demo)

        recyclerView.findViewById<RecyclerView>(R.id.recycler_view_product)

        // on below line we are creating a retrofit
        // builder and passing our base url
        // on below line we are creating a retrofit
        // builder and passing our base url
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")

            // on below line we are calling add Converter
            // factory as GSON converter factory.
            // at last we are building our retrofit builder.
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)





        getProductData(retrofit.getProducts())
    }
    private fun getProductData(getProduct: Call<ProductList>) {
        val call: Call<ProductList> = getProduct
        call.enqueue(object : Callback<ProductList> {
            override fun onResponse(call: Call<ProductList>, response: Response<ProductList>) {
                findViewById<ProgressBar>(R.id.progress_products).visibility = View.GONE
                recyclerView.visibility = View.VISIBLE

                viewProduct(response.body()?.products)


            }

            override fun onFailure(call: Call<ProductList>, t: Throwable) {
                Toast.makeText(this@Retrofrit_Demo, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
    private fun viewProduct(productsList: ArrayList<Product>?) {
        if(productsList != null){
            productAdapter = ProductAdapter(this)
            productAdapter.setProductData(productsList)
            recyclerView.adapter = productAdapter
        }else{
            Toast.makeText(this, "No Product Available", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onClick(position: Int, productList: ArrayList<Product>) {

        val data = productList[position]
        val productsDetail = Product(data.id,data.title,data.description,data.price,data.discountPercentage,data.rating,data.stock,data.brand,data.category,data.thumbnail,data.images)
        val intent = Intent(this,ProductDetailActivity::class.java)
        intent.putExtra("details",productsDetail)
        startActivity(intent)
    }

//        // on below line we are creating a retrofit
//        // builder and passing our base url
//        // on below line we are creating a retrofit
//        // builder and passing our base url
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dummyjson.com/")
//
//            // on below line we are calling add Converter
//            // factory as GSON converter factory.
//            // at last we are building our retrofit builder.
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        // below line is to create an instance for our retrofit api class.
//        // below line is to create an instance for our retrofit api class.
//        val retrofitAPI = retrofit.create(ProductApi::class.java)

//        val call: Call<ProductList?>? = retrofitAPI.getProducts()
//
//        // on below line we are making a call.
//        call!!.enqueue(object : Callback<ProductList?> {
//            override fun onResponse(call: Call<ProductList?>, response: Response<ProductList?>) {
//                response.body()?.products.toString()
//            }
//
//
//            override fun onFailure(call: Call<ProductList?>, t: Throwable) {
//                // displaying an error message in toast
//                Toast.makeText(this@Retrofrit_Demo, "Fail to get the data..", Toast.LENGTH_SHORT)
//                    .show()
//            }
//        })
}















