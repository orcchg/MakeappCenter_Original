package com.orcchg.makeappcenter.app.common.widget

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.common.ImageHolder
import com.orcchg.makeappcenter.data.eventbus.ProductToCartEvent
import com.orcchg.makeappcenter.domain.model.Product
import org.greenrobot.eventbus.EventBus
import java.math.BigDecimal
import java.text.NumberFormat

class ProductCard : LinearLayout {

    @BindView(R.id.tv_price) lateinit var price: TextView
    @BindView(R.id.tv_title) lateinit var title: TextView
    private val imageHolder = ImageHolder(context)

    private var product: Product? = null
    private var onCartClickListener: ((view: View) -> Unit)? = null

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
        imageHolder.init(rootView)
        orientation = VERTICAL

        price.setOnClickListener({
            if (product != null) {
                EventBus.getDefault().post(ProductToCartEvent(product!!))
            }
            onCartClickListener?.invoke(it)
        })
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun setProduct(product: Product) {
        this.product = product
        setCover(product.coverUrl)
        setPrice(product.price)
        setTitle(product.title)
    }

    // ------------------------------------------
    private fun setCover(url: String) {
        imageHolder.setCover(url)
    }

    private fun setPrice(value: BigDecimal) {
        price.text = CURRENCY_FORMAT.format(value)
    }

    private fun setTitle(text: String) {
        title.text = text
    }

    private fun setTitle(@StringRes textResId: Int) {
        title.setText(textResId)
    }

    /* Listener */
    // ------------------------------------------
    fun setOnCartClickListener(l: ((view: View) -> Unit)?) {
        onCartClickListener = l
    }
}
