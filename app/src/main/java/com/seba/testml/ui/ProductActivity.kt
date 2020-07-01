package com.seba.testml.ui

import android.os.Bundle
import com.seba.testml.databinding.ActivityProductBinding
import com.seba.testml.srv.dto.Product
import com.squareup.picasso.Picasso

class ProductActivity : BaseActivity() {

    private var product: Product? = null
    private var bind: ActivityProductBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductBinding.inflate(layoutInflater)
        setContentView(bind!!.root)
        val intent = intent
        if (intent != null && intent.hasExtra(Product.KEY_PRODUCT)) product = intent.getParcelableExtra(Product.KEY_PRODUCT)
        setProductView()
    }

    private fun setProductView() {
        if (product != null) {
            Picasso.get().load(product!!.thumbnail).into(bind!!.thumbnailIV)
            bind!!.conditionTV.text = product!!.condition
            bind!!.titleTV.text = product!!.title
            bind!!.originalPriceTV.text = product!!.original_price.toString()
            bind!!.priceTV.text = product!!.price.toString()
        }
    }
}