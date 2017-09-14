package com.orcchg.makeappcenter.app.view.main

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import butterknife.BindView
import butterknife.ButterKnife
import com.ncapdevi.fragnav.FragNavController
import com.orcchg.makeappcenter.app.R
import com.orcchg.makeappcenter.app.navigation.FragmentNavigationHandler
import com.orcchg.makeappcenter.app.navigation.OpenScreenEvent
import com.orcchg.makeappcenter.app.navigation.Screen
import com.orcchg.makeappcenter.app.view.base.BaseActivity
import com.orcchg.makeappcenter.app.view.cart.CartFragment
import com.orcchg.makeappcenter.app.view.favorite.FavoriteListFragment
import com.orcchg.makeappcenter.app.view.home.HomeFragment
import com.orcchg.makeappcenter.app.view.location.LocationFragment
import com.orcchg.makeappcenter.app.view.product.details.ProductDetailsFragment
import com.orcchg.makeappcenter.app.view.profile.ProfileFragment
import com.orcchg.makeappcenter.data.eventbus.ProductAddToCartEvent
import com.orcchg.makeappcenter.data.viewmodel.CartViewModel
import com.orcchg.makeappcenter.domain.model.Product
import com.roughike.bottombar.BottomBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(), FragNavController.RootFragmentListener,
        FragNavController.TransactionListener, FragmentNavigationHandler {

    @BindView(R.id.bottom_bar) lateinit var bottomBar: BottomBar

    companion object {
        /**
         * Navigation indices are used by [FragNavController] to specify each [Fragment].
         */
        const val NAVIGATION_INDEX_HOME = 0
        const val NAVIGATION_INDEX_LOCATION = 1
        const val NAVIGATION_INDEX_FAVORITES = 2
        const val NAVIGATION_INDEX_PROFILE = 3
        const val NAVIGATION_INDEX_CART = 4
        const val TOTAL_TABS = 5

        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    private lateinit var vm: CartViewModel

    // ------------------------------------------
    private lateinit var fragmentNavigationController: FragNavController

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        initView(savedInstanceState)
        initBottomBar()

        EventBus.getDefault().register(this)

        vm = ViewModelProviders.of(this, viewModelComponent.cartFactory()).get(CartViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        fragmentNavigationController.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    /* View */
    // --------------------------------------------------------------------------------------------
    private fun initView(savedInstanceState: Bundle?) {
        fragmentNavigationController = FragNavController.newBuilder(savedInstanceState, supportFragmentManager, R.id.container)
                .rootFragmentListener(this, TOTAL_TABS)
                .selectedTabIndex(FragNavController.NO_TAB)  // don't show any fragment at beginning
                .transactionListener(this)
                .build()
    }

    /**
     * Sets tab selection listeners for the [BottomBar].
     */
    @SuppressLint("ResourceType")
    private fun initBottomBar() {
        bottomBar.setOnTabSelectListener {
            when (it) {
                R.id.tab_home      -> fragmentNavigationController.switchTab(NAVIGATION_INDEX_HOME)
                R.id.tab_location  -> fragmentNavigationController.switchTab(NAVIGATION_INDEX_LOCATION)
                R.id.tab_favorites -> fragmentNavigationController.switchTab(NAVIGATION_INDEX_FAVORITES)
                R.id.tab_profile   -> fragmentNavigationController.switchTab(NAVIGATION_INDEX_PROFILE)
                R.id.tab_cart      -> fragmentNavigationController.switchTab(NAVIGATION_INDEX_CART)
            }
        }

        bottomBar.setOnTabReselectListener { fragmentNavigationController.clearStack() }
    }

    /* Navigation */
    // --------------------------------------------------------------------------------------------
    override fun getRootFragment(index: Int): Fragment {
        return when (index) {
            NAVIGATION_INDEX_HOME      -> HomeFragment.newInstance()
            NAVIGATION_INDEX_LOCATION  -> LocationFragment.newInstance()
            NAVIGATION_INDEX_FAVORITES -> FavoriteListFragment.newInstance()
            NAVIGATION_INDEX_PROFILE   -> ProfileFragment.newInstance()
            NAVIGATION_INDEX_CART      -> CartFragment.newInstance()
            else -> throw IllegalStateException("Need to send an index that we know")
        }
    }

    override fun onFragmentTransaction(fragment: Fragment, transactionType: FragNavController.TransactionType) {
    }

    override fun onTabTransaction(fragment: Fragment, index: Int) {
    }

    // ------------------------------------------
    override fun onBackPressed() {
        if (!fragmentNavigationController.isRootFragment) {
            fragmentNavigationController.popFragment()
        } else {
            super.onBackPressed()
        }
    }

    override fun pushFragment(fragment: Fragment) {
        fragmentNavigationController.pushFragment(fragment)
    }

    /* Event Bus */
    // --------------------------------------------------------------------------------------------
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onOpenScreenEvent(event: OpenScreenEvent) {
        when (event.screen) {
            Screen.PRODUCT_DETAILS -> {
                val product = event.payload as Product
                pushFragment(ProductDetailsFragment.newInstance(product.id))
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onProductAddToCartEvent(event: ProductAddToCartEvent) {
        vm.cartSize().subscribe { bottomBar.getTabWithId(R.id.tab_cart).setBadgeCount(it) }
    }
}
