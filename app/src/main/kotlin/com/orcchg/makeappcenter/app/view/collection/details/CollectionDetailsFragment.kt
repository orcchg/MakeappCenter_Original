package com.orcchg.makeappcenter.app.view.collection.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.product.ProductsGridAdapter
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class CollectionDetailsFragment : BaseFragment() {

    @BindView(R.id.iv_cover) lateinit var cover: ImageView
    @BindView(R.id.rv_products) lateinit var products: RecyclerView
    @BindView(R.id.progressbar) lateinit var progressbar: View

    companion object {
        private const val BUNDLE_KEY_COLLECTION_ID = "BUNDLE_KEY_COLLECTION_ID"

        fun newInstance(collectionId: String): CollectionDetailsFragment {
            val args = Bundle()
            args.putString(BUNDLE_KEY_COLLECTION_ID, collectionId)
            val fragment = CollectionDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var collectionId: String

    private lateinit var vm: ProductViewModel

    private lateinit var adapter: ProductsGridAdapter

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        collectionId = arguments.getString(BUNDLE_KEY_COLLECTION_ID)
        super.onCreate(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelComponent.productFactory()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_collection_details, container, false)
        ButterKnife.bind(this, rootView)

        adapter = ProductsGridAdapter()
        products.layoutManager = GridLayoutManager(context, 2)
        products.adapter = adapter
        OverScrollDecoratorHelper.setUpOverScroll(products, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.collection(collectionId)
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe {
                    Glide.with(context).load(it.coverUrl).into(cover)
                    adapter.items = it.products
                }
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
