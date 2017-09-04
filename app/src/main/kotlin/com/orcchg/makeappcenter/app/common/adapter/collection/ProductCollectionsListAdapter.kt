package com.orcchg.makeappcenter.app.common.adapter.collection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.domain.model.ProductCollection

class ProductCollectionsListAdapter : RecyclerView.Adapter<ProductCollectionViewHolder>() {

    var items: List<ProductCollection> = ArrayList()
        set(l) {
            field = l
            notifyDataSetChanged()
        }

    /* Adapter */
    // --------------------------------------------------------------------------------------------
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_collections_list_item_layout, parent, false)
        return ProductCollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductCollectionViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
