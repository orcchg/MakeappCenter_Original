package com.orcchg.makeappcenter.app.common.adapter.product

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.common.ImageHolder
import com.orcchg.makeappcenter.domain.model.Product

class ProductInCartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.tv_title) lateinit var title: TextView
    private val imageHolder = ImageHolder(itemView.context)

    init {
        ButterKnife.bind(this, itemView)
        imageHolder.init(itemView)
    }
    
    fun bind(model: Product) {
        imageHolder.setCover(model.coverUrl)
        title.text = model.title
    }
}
