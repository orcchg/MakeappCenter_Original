package com.orcchg.makeappcenter.app.common.widget

import android.content.Context
import android.support.annotation.StringRes
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.widget.common.ImageHolder
import com.orcchg.makeappcenter.domain.model.ProductCollection

class CollectionCard : ConstraintLayout {

    @BindView(R.id.tv_title) lateinit var title: TextView
    private val imageHolder = ImageHolder(context)

    constructor(context: Context): this(context, null)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    // ------------------------------------------
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        val rootView = LayoutInflater.from(context).inflate(R.layout.widget_collection_card_layout, this, true)
        ButterKnife.bind(rootView)
        imageHolder.init(rootView)
    }

    /* API */
    // --------------------------------------------------------------------------------------------
    fun setCover(url: String) {
        imageHolder.setCover(url)
    }

    fun setCollection(collection: ProductCollection) {
        setCover(collection.coverUrl)
        setTitle(collection.title)
    }

    fun setTitle(text: String) {
        title.text = text
    }

    fun setTitle(@StringRes textResId: Int) {
        title.setText(textResId)
    }
}
