package com.orcchg.makeappcenter.data.viewmodel.product

import android.arch.lifecycle.ViewModel
import com.orcchg.makeappcenter.data.repository.product.ProductRepository
import com.orcchg.makeappcenter.domain.model.Product
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    fun collections() = repository.collections()

    fun products(): Flowable<List<Product>> = repository.products().observeOn(AndroidSchedulers.mainThread())
    fun productsForCollection(collectionId: String) = repository.productsForCollection(collectionId)
}
