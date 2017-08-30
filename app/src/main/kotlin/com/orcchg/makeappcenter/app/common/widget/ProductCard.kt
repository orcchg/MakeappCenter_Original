package com.orcchg.makeappcenter.app.common.widget

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.orcchg.makeappcenter.app.R
import java.math.BigDecimal
import java.text.NumberFormat

class ProductCard : LinearLayout {

    @BindView(R.id.iv_cover) lateinit var cover: ImageView
    @BindView(R.id.tv_price) lateinit var price: TextView
    @BindView(R.id.tv_title) lateinit var title: TextView

    companion object {
        val CURRENCY_FORMAT = NumberFormat.getCurrencyInstance()
    }

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs, defStyleAttr)
    }

    // ------------------------------------------
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val rootView = LayoutInflater.from(context).inflate(R.layout.widget_product_card_layout, this, true)
        ButterKnife.bind(rootView)

        orientation = VERTICAL
        minimumHeight = resources.getDimensionPixelSize(R.dimen.widget_product_card_height)
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun setCover(url: String) {
        Glide.with(context).load(url).into(cover)
    }

    fun setPrice(value: BigDecimal) {
        price.text = CURRENCY_FORMAT.format(value)
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setTitle(@StringRes textResId: Int) {
        title.setText(textResId)
    }
}
