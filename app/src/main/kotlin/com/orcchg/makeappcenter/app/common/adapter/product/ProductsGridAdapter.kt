package com.orcchg.makeappcenter.app.common.adapter.product

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.domain.model.Product

class ProductsGridAdapter() : RecyclerView.Adapter<ProductGridViewHolder>() {

    var items: List<Product> = ArrayList()
        set(l) {
            field = l
            notifyDataSetChanged()
        }

    /* Adapter */
    // --------------------------------------------------------------------------------------------
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductGridViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.products_grid_item_layout, parent, false)
        return ProductGridViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductGridViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
