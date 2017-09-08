package com.orcchg.makeappcenter.app.view.favorite

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.common.adapter.collection.CollectionsListAdapter
import com.orcchg.makeappcenter.app.navigation.FragmentNavigationHandler
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.app.view.collection.details.CollectionDetailsFragment
import com.orcchg.makeappcenter.data.viewmodel.product.ProductViewModel
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class FavoriteListFragment : BaseFragment() {

    @BindView(R.id.rv_collections) lateinit var collections: RecyclerView
    @BindView(R.id.progressbar) lateinit var progressbar: View

    private var fragmentNavigation: FragmentNavigationHandler? = null

    private lateinit var vm: ProductViewModel

    private lateinit var adapter: CollectionsListAdapter

    companion object {
        fun newInstance(): FavoriteListFragment {
            return FavoriteListFragment()
        }
    }

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentNavigationHandler) fragmentNavigation = context
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm = ViewModelProviders.of(this, viewModelComponent.productFactory()).get(ProductViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView = inflater.inflate(R.layout.fragment_favorite_list, container, false)
        ButterKnife.bind(this, rootView)

        adapter = CollectionsListAdapter({ (id), _ ->
            fragmentNavigation?.pushFragment(CollectionDetailsFragment.newInstance(id))
        })
        collections.layoutManager = LinearLayoutManager(context)
        collections.adapter = adapter
        OverScrollDecoratorHelper.setUpOverScroll(collections, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)

        return rootView
    }

    override fun onStart() {
        super.onStart()
        vm.collections()
                .doOnSubscribe { showLoading(true) }
                .doFinally { showLoading(false) }
                .subscribe({ adapter.items = it })
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun showLoading(isVisible: Boolean) {
        progressbar.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}
