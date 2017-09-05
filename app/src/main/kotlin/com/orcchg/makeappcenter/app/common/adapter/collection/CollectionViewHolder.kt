package com.orcchg.makeappcenter.app.common.adapter.collection

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.CollectionCard
import com.orcchg.makeappcenter.domain.model.ProductCollection

class CollectionViewHolder(itemView: View, val l: (collection: ProductCollection, position: Int) -> Unit)
    : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.collection_card) lateinit var collection: CollectionCard

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(model: ProductCollection) {
        collection.setOnClickListener({ l.invoke(model, adapterPosition) })
        collection.setCollection(model)
    }
}
