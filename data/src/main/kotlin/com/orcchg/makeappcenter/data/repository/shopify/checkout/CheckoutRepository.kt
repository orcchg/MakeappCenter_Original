package com.orcchg.makeappcenter.data.repository.shopify.checkout

import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.repository.Rx
import com.orcchg.makeappcenter.data.source.local.shopify.checkout.CheckoutDao
import com.orcchg.makeappcenter.data.source.remote.shopify.checkout.CheckoutCloud
import com.orcchg.makeappcenter.domain.model.Checkout
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CheckoutRepository @Inject constructor(private val checkoutCloud: CheckoutCloud,
                                             private val checkoutDao: CheckoutDao)
    : Repository() {

    fun createCheckout(products: Collection<Product>): Flowable<Checkout> {
        // TODO: from local
        return checkoutCloud.checkout(products).compose(Rx.flowableTransformer())
    }
}
