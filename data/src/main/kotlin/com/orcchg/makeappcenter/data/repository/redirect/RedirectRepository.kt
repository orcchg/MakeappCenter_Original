package com.orcchg.makeappcenter.data.repository.redirect

import com.orcchg.makeappcenter.data.repository.Repository
import com.orcchg.makeappcenter.data.source.remote.shopify.redirect.RedirectCloud
import com.orcchg.makeappcenter.domain.model.Redirect
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedirectRepository @Inject constructor(private val redirectCloud: RedirectCloud) : Repository() {

    fun redirects(): Flowable<Redirect> {
        // TODO: from local
        return redirectCloud.redirects().flatMapIterable { it.redirects }
    }
}
