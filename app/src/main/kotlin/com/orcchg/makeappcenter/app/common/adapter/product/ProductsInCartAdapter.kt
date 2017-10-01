package com.orcchg.makeappcenter.app.common.adapter.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.domain.model.Product

class ProductsInCartAdapter : RecyclerView.Adapter<ProductInCartViewHolder>() {

    var items: MutableList<Product> = ArrayList()
        set(l) {
            field = l
            notifyDataSetChanged()
        }

    /* Adapter */
    // --------------------------------------------------------------------------------------------
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInCartViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_in_cart_item_layout, parent, false)
        return ProductInCartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductInCartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}