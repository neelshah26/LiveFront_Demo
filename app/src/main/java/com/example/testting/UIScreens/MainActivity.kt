package com.example.testting.UIScreens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar

import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.testting.ProductAdapter
import com.example.testting.R
import com.example.testting.models.ItemDetails

class MainActivity : AppCompatActivity() {

    // on below line we are creating variables for
    // our Recycle view and progress bar
    lateinit var productRV: RecyclerView
    lateinit var loadingPB: ProgressBar
    lateinit var productAdapter: ProductAdapter
    lateinit var productList: ArrayList<ItemDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // on below line we are initializing our variable with their ids.
        productRV = findViewById(R.id.idRVCourses)
        loadingPB = findViewById(R.id.idPBLoading)

        productList = ArrayList()

        productAdapter = ProductAdapter(productList)

        productRV.adapter = productAdapter

        getData()

    }

    private fun getData() {
        var url = "https://dummyjson.com/products/"

        // on below line we are creating a
        // variable for our request queue
        val queue = Volley.newRequestQueue(this@MainActivity)

        // on below line we are creating a request
        // variable for making our json object request.
        val request =
            // as we are getting json object response and we are making a get request.
            JsonObjectRequest(Request.Method.GET, url, null, { response ->
                // this method is called when we get successful response from API.
                loadingPB.visibility = View.GONE
                try {
                        // on below line we are extracting
                        // data from each json object
                        Log.d("len", response.length().toString())
                        // we are getting the List of products
                        val productresponse = response.getJSONArray("products")
                        for(i in 0 until productresponse.length()) {
                            val respObj = productresponse.getJSONObject(i)
                            val id = respObj.getInt("id")
                            val title = respObj.getString("title")
                            val desc = respObj.getString("description")
                            val price = respObj.getInt("price")
                            val discount = respObj.getInt("discountPercentage")
                            val rating = respObj.getDouble("rating")
                            val stock = respObj.getInt("stock")
                            val brand = respObj.getString("brand")
                            val category = respObj.getString("category")
                            val thumbnail = respObj.getString("thumbnail")


                            // on below line we are adding data to our list
                            productList.add(
                                ItemDetails(
                                    id,
                                    title,
                                    desc,
                                    price,
                                    discount,
                                    rating.toFloat(),
                                    stock,
                                    brand,
                                    category,
                                    thumbnail,
                                    null
                                )
                            )

                            // on below line we are notifying
                            // our adapter that data has updated.
                            productAdapter.notifyDataSetChanged()
                        }

                    // on below line we
                    // are handling exception
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }, { error ->
                // in this case we are simply displaying a toast message.
                Toast.makeText(this@MainActivity, "Fail to get response", Toast.LENGTH_SHORT)
                    .show()
            })
        // at last we are adding our
        // request to our queue.
        queue.add(request)
    }
}




