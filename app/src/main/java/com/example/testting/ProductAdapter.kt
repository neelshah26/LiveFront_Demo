package com.example.testting

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testting.UIScreens.ItemDetail
import com.example.testting.models.ItemDetails
import com.squareup.picasso.Picasso
import java.io.Serializable

// on below line we are creating a course rv adapter class.

private lateinit var context: Context
class ProductAdapter(
    // on below line we are passing variables as course list and context
    private var productList: ArrayList<ItemDetails>,
) : RecyclerView.Adapter<ProductAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.CourseViewHolder {
        // this method is use to inflate the layout file
        // which we have created for our recycler view.
        // on below line we are inflating our layout file.
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.product_cardview,
            parent, false
        )
        context = parent.context
        // at last we are returning our view
        // holder class with our item View File.
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.CourseViewHolder, position: Int) {
        // on below line we are setting data to our text view and our image view.
        holder.titleTV.text = productList.get(position).title
        val cat: String = "->" + productList.get(position).category
        holder.titleTV.setOnClickListener(View.OnClickListener {
            val intent = Intent(it.context, ItemDetail::class.java)
            intent.putExtra("item_data", productList.get(position) as Serializable)
            context.startActivity(intent)
            //Toast.makeText(context,cat,Toast.LENGTH_SHORT).show()
        })
        Picasso.get().load(productList.get(position).thumbnail).into(holder.displayIV)
        val price: String = "Price: $" + productList.get(position).price.toString()
        holder.priceTV.text = price
    }

    override fun getItemCount(): Int {
        // on below line we are returning
        // our size of our list
        return productList.size
    }


    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are initializing our course name
        // text view and our image view.
        val titleTV: TextView = itemView.findViewById(R.id.tvname)
        val displayIV: ImageView = itemView.findViewById(R.id.ivdisplay)
        val priceTV: TextView = itemView.findViewById(R.id.tvprice)

    }
}
