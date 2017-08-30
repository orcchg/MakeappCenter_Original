package com.orcchg.makeappcenter.app.common.adapter.collection

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.domain.model.ProductCollection

class CollectionsListAdapter : RecyclerView.Adapter<CollectionViewHolder>() {

    var items: List<ProductCollection> = ArrayList()
        set(l) {
            field = l
            notifyDataSetChanged()
        }

    /* Adapter */
    // --------------------------------------------------------------------------------------------
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.collections_list_item_layout, parent, false)
        return CollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        holder.bind(items[position])
    }
}
