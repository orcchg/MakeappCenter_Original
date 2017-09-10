package com.orcchg.makeappcenter.data.repository.product

import com.orcchg.makeappcenter.data.eventbus.ProductToCartEvent
import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.repository.RepositoryUtility
import com.orcchg.makeappcenter.data.source.local.product.CartDao
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor(private val cartDao: CartDao)
    : Repository() {

    init {
        EventBus.getDefault().register(this)
    }

    fun productsInCart() = cartDao.productsInCart()
            .compose(RepositoryUtility.mainTransformer())

    /* Event Bus */
    // --------------------------------------------------------------------------------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onProductToCartEvent(event: ProductToCartEvent) {
        Timber.d("Event: $event")
        cartDao.addProductToCart(event.product)
    }
}
