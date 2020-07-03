package com.seba.testml.ui

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.seba.testml.databinding.ActivityProductBinding
import com.seba.testml.srv.dto.Product
import com.seba.testml.utils.Utils.formatPrice
import com.squareup.picasso.Picasso
import kotlin.math.roundToInt

class ProductActivity : BaseActivity() {

    private var product: Product? = null
    private var bind: ActivityProductBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductBinding.inflate(layoutInflater)
        setContentView(bind!!.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "Producto"
        supportActionBar!!.subtitle = "Visualización de ejemplo"
        val intent = intent
        if (intent != null && intent.hasExtra(Product.KEY_PRODUCT)) product = intent.getParcelableExtra(Product.KEY_PRODUCT)
        setProductView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setProductView() {
        if (product != null) {
            Picasso.get().load(product!!.thumbnail).into(bind!!.thumbnailIV)


            if (product!!.condition == "new") bind!!.conditionTV.text = "Nuevo"
            else bind!!.conditionTV.visibility = View.GONE

            bind!!.titleTV.text = product!!.title

            bind!!.originalPriceTV.visibility = View.GONE
            bind!!.percentTV.visibility = View.GONE
            if (product!!.original_price > 0f) {
                bind!!.originalPriceTV.visibility = View.VISIBLE
                bind!!.percentTV.visibility = View.VISIBLE
                bind!!.originalPriceTV.text = formatPrice(product!!.original_price, "$", 0)
                val percent = ((product!!.original_price - product!!.price) / product!!.original_price * 100).roundToInt()
                bind!!.percentTV.text = "$percent% OFF"
            }

            bind!!.priceTV.text = formatPrice(product!!.price, "$", 0)

        }
    }
}