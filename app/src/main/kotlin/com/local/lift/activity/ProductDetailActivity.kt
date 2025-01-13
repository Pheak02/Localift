package com.local.lift.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.local.locallift.R
import com.squareup.picasso.Picasso

class ProductDetailActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val productImage=intent.getStringExtra("product_image")
        val productName=intent.getStringExtra("product_name")
        val productPrice=intent.getStringExtra("product_price")
        val productDescription=intent.getStringExtra("product_description")

        val imageProductDetail=findViewById<ImageView>(R.id.productDetail)
        val nameProductDetail=findViewById<TextView>(R.id.productNameDetail)
        val priceProductDetail=findViewById<TextView>(R.id.productPriceDetail)
        val descriptionDetail=findViewById<TextView>(R.id.productDescription)

        Picasso.get().load(productImage).into(imageProductDetail)
        nameProductDetail.text=productName
        priceProductDetail.text=productPrice
        descriptionDetail.text=productDescription
    }
}