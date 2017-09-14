package com.orcchg.makeappcenter.app.view.product.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.shopify.product.ProductViewModel

class ProductDetailsFragment : BaseFragment() {

    @BindView(R.id.iv_cover) lateinit var cover: ImageView
    @BindView(R.id.progressbar) lateinit var progressbar: View
    @BindView(R.id.tv_description) lateinit var description: TextView
    @BindView(R.id.tv_title) lateinit var title: TextView

    companion object {
        private const val BUNDLE_KEY_PRODUCT_ID = "BUNDLE_KEY_PRODUCT_ID"

        fun newInstance(productId: String): ProductDetailsFragment {
            val args = Bundle()
            args.putString(BUNDLE_KEY_PRODUCT_ID, productId)
            val fragment = ProductDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var productId: String

    private lateinit var vm: ProductViewModel

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        productId = arguments.getString(BUNDLE_KEY_PRODUCT_ID)
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelComponent.productFactory()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_product_details, container, false)
        ButterKnife.bind(this, rootView)
        // TODO:
        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.product(productId)
            .doOnSubscribe { showLoading(true) }
            .doFinally { showLoading(false) }
            .subscribe {
                Glide.with(context).load(it.coverUrl).into(cover)
                description.text = it.description
                title.text = it.title
            }
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
