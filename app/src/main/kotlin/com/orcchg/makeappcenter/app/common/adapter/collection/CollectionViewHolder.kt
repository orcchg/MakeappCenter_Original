package com.orcchg.makeappcenter.app.common.adapter.collection

import android.support.v4.view.ViewPager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.product.ProductsPagerAdapter
import com.orcchg.makeappcenter.domain.model.ProductCollection
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import timber.log.Timber

class CollectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_title) lateinit var title: TextView
    @BindView(R.id.vp_products) lateinit var products: ViewPager

    private val adapter = ProductsPagerAdapter(itemView.context)

    init {
        ButterKnife.bind(this, itemView)
        products.adapter = adapter
        OverScrollDecoratorHelper.setUpOverScroll(products)
    }

    fun bind(model: ProductCollection) {
        Timber.e("Collection[${model.products.size}]: ${model.title}")
        model.products.forEach { Timber.w("Product: ${it.title}") }
        title.text = model.title
        adapter.items = model.products
    }
}
