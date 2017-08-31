package com.orcchg.makeappcenter.app.common.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.domain.model.Product
import java.math.BigDecimal
import java.text.NumberFormat

class ProductCard : LinearLayout {

    @BindView(R.id.iv_cover) lateinit var cover: ImageView
    @BindView(R.id.iv_placeholder) lateinit var coverPlaceholder: ImageView
    @BindView(R.id.tv_price) lateinit var price: TextView
    @BindView(R.id.tv_title) lateinit var title: TextView
    @BindView(R.id.progressbar) lateinit var progressBar: View

    companion object {
        val CURRENCY_FORMAT: NumberFormat = NumberFormat.getCurrencyInstance()
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

        minimumHeight = resources.getDimensionPixelSize(R.dimen.widget_product_card_height)
        orientation = VERTICAL
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun setCover(url: String) {
        progressBar.visibility = View.VISIBLE
        Glide.with(context).load(url)
                .listener(object : RequestListener<Drawable> {
                    override fun onResourceReady(resource: Drawable, model: Any, target: Target<Drawable>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.INVISIBLE
                        cover.visibility = View.VISIBLE
                        coverPlaceholder.visibility = View.INVISIBLE
                        return false
                    }

                    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean): Boolean {
                        progressBar.visibility = View.INVISIBLE
                        cover.visibility = View.INVISIBLE
                        coverPlaceholder.visibility = View.VISIBLE
                        return true
                    }
                })
                .into(cover)
    }

    fun setPrice(value: BigDecimal) {
        price.text = CURRENCY_FORMAT.format(value)
    }

    fun setProduct(product: Product) {
        setCover(product.coverUrl)
        setPrice(product.price)
        setTitle(product.title)
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setTitle(@StringRes textResId: Int) {
        title.setText(textResId)
    }
}
