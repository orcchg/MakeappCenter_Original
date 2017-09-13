package com.orcchg.makeappcenter.app.common.widget

import android.content.Context
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.coordinator.ProductCardCoordinator
import com.orcchg.makeappcenter.app.common.widget.common.ImageHolder
import com.orcchg.makeappcenter.app.navigation.OpenScreenEvent
import com.orcchg.makeappcenter.app.navigation.Screen
import com.orcchg.makeappcenter.data.eventbus.ProductAddToCartEvent
import com.orcchg.makeappcenter.domain.model.Product
import com.squareup.coordinators.Coordinators
import org.greenrobot.eventbus.EventBus
import java.math.BigDecimal
import java.text.NumberFormat

class ProductCard : LinearLayout {

    @BindView(R.id.ll_container) lateinit var container: ViewGroup
    @BindView(R.id.tv_title) lateinit var title: TextView
    private val imageHolder = ImageHolder(context)
    private lateinit var cartLabel: TextView
    private lateinit var priceLabel: TextView

    private var product: Product? = null

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
        val inflater = LayoutInflater.from(context)
        val rootView = inflater.inflate(R.layout.widget_product_card_layout, this, true)
        ButterKnife.bind(rootView)
        imageHolder.init(rootView)
        orientation = VERTICAL

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.topMargin = resources.getDimensionPixelSize(R.dimen.std_padding_mini)
        params.marginStart = resources.getDimensionPixelSize(R.dimen.widget_product_card_margin_side_price_label)
        params.marginEnd = resources.getDimensionPixelSize(R.dimen.widget_product_card_margin_side_price_label)

        cartLabel = inflater.inflate(R.layout.widget_price_label_carted_layout, container, false) as TextView
        priceLabel = inflater.inflate(R.layout.widget_price_label_layout, container, false) as TextView
        cartLabel.layoutParams = params
        priceLabel.layoutParams = params

        container.addView(priceLabel)

        cartLabel.setOnClickListener {
            if (product != null) {
                // TODO:
            }
        }
        priceLabel.setOnClickListener({
            if (product != null) {
                container.removeView(priceLabel)
                container.addView(cartLabel)
                EventBus.getDefault().post(ProductAddToCartEvent(product!!))
            }
        })

        imageHolder.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (product != null) {
                    EventBus.getDefault().post(OpenScreenEvent(Screen.PRODUCT_DETAILS, product!!))
                }
            }
        })

        Coordinators.bind(this, { ProductCardCoordinator() })
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
        priceLabel.text = CURRENCY_FORMAT.format(value)
    }

    private fun setTitle(text: String) {
        title.text = text
    }

    private fun setTitle(@StringRes textResId: Int) {
        title.setText(textResId)
    }

    /* Listener */
    // --------------------------------------------------------------------------------------------
}
