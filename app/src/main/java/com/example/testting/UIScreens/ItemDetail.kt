package com.example.testting.UIScreens

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testting.R
import com.example.testting.models.ItemDetails
import com.squareup.picasso.Picasso

class ItemDetail : AppCompatActivity(){

    lateinit var IVthumb: ImageView
    lateinit var tvtitle: TextView
    lateinit var tvbrand: TextView
    lateinit var tvprice: TextView
    lateinit var tvcategory: TextView
    lateinit var tvrating: TextView
    lateinit var rbrating : RatingBar
    lateinit var tvdescp : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_detail)
        val data: ItemDetails? = intent.getSerializableExtra("item_data") as? ItemDetails
        IVthumb = findViewById(R.id.ivthumbnail)
        tvtitle = findViewById(R.id.tvtitle)
        tvbrand = findViewById(R.id.tvbrand)
        tvprice = findViewById(R.id.tvPrice)
        tvcategory = findViewById(R.id.tvcategory)
        tvrating = findViewById(R.id.tvrating)
        rbrating = findViewById(R.id.rbrating)
        tvdescp = findViewById(R.id.tvdescription)
        tvtitle.text = data?.title
        val brand: String = "Brand: " + data?.brand?.capitalize()
        tvbrand.text = brand
        val price: String = "Price: $" + data?.price.toString()
        tvprice.text = price
        val category: String? = data?.category?.capitalize()
        tvcategory.text = category
        //tvrating.text = data?.rating.toString()
        val descrp: String? = data?.description
        tvdescp.text = descrp
        Picasso.get().load(data?.thumbnail).into(IVthumb)
        rbrating.rating = data?.rating!!
    }
}