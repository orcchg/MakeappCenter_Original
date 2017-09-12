package com.orcchg.makeappcenter.app.common.adapter.product

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.ProductCard
import com.orcchg.makeappcenter.domain.model.Product

class ProductGridViewHolder(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.product_card) lateinit var product: ProductCard

    init {
        ButterKnife.bind(this, itemView)
    }

    fun bind(model: Product) {
        product.setProduct(model)
    }
}
