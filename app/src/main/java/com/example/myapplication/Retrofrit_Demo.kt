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






class Retrofrit_Demo : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofrit_demo)



        recyclerView=findViewById(R.id.recycler_view)

        println(getData())
    }
    private fun getData() {
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

        // below line is to create an instance for our retrofit api class.
        // below line is to create an instance for our retrofit api class.
        val retrofitAPI = retrofit.create(ProductApi::class.java)

        val call: Call<ProductList> = retrofitAPI.getProducts()

        // on below line we are making a call.
        call.enqueue(object : Callback<ProductList?> {
            override fun onResponse(call: Call<ProductList?>, response: Response<ProductList?>) {
                val retrofit2= response.body()?.let {ProductAdapter(it.products)}
                recyclerView.adapter = retrofit2

                retrofit2!!.setOnClickListener(object : ProductAdapter.OnClickListener {
                    override fun onClick(position: Int, productlist: MutableList<Product>) {
                        val intent = Intent(this@Retrofrit_Demo, ProductDetailActivity::class.java)
                        val products = productlist[position]
                        val data = Product(products.id,products.title,products.description,products.price,
                            products.discountPercentage,products.rating,products.stock,products.brand,
                            products.category,products.thumbnail,products.images)
                       // intent.putExtra("Product_Data",data)
                        //intent.putExtra("Product_Data",data)
                        startActivity(intent)
                    }
                })
            }
            override fun onFailure(call: Call<ProductList?>, t: Throwable) {
                // displaying an error message in toast
                Toast.makeText(this@Retrofrit_Demo, "Fail to get the data..", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}












