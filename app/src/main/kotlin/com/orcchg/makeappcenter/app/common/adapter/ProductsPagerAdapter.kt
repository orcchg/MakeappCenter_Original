package com.orcchg.makeappcenter.app.common.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.ProductCard
import com.orcchg.makeappcenter.domain.model.Product

class ProductsPagerAdapter(private val context: Context) : PagerAdapter() {

    var items: List<Product> = ArrayList()
        set(l) {
            field = l
            notifyDataSetChanged()
        }

    /* Adapter */
    // --------------------------------------------------------------------------------------------
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val card = ProductCard(context)
        card.setCover(items[position].coverUrl)
        card.setTitle(items[position].title)
        container.addView(card)
        return card
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        val card = view as View
        container.removeView(card)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return items.size
    }

    /* Internal */
    // --------------------------------------------------------------------------------------------
    override fun getPageWidth(position: Int): Float {
        val typedValue = TypedValue()
        context.resources.getValue(R.dimen.product_cards_viewpager_page_width_fraction, typedValue, false)
        return typedValue.float
    }
}
