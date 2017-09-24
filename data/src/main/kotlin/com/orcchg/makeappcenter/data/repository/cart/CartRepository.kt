package com.orcchg.makeappcenter.data.repository.cart

import com.orcchg.makeappcenter.data.eventbus.ProductAddToCartEvent
import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.repository.Rx
import com.orcchg.makeappcenter.data.source.local.cart.CartDao
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

    fun cartSize() = cartDao.cartSize().compose(Rx.flowableTransformer())

    fun productsInCart() = cartDao.productsInCart()
            .compose(Rx.maybeTransformer())

    /* Event Bus */
    // --------------------------------------------------------------------------------------------
    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun onProductAddToCartEvent(event: ProductAddToCartEvent) {
        Timber.d("Event: $event")
        cartDao.addProductToCart(event.product)
    }
}
