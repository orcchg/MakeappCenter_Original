package com.orcchg.makeappcenter.domain.model

import com.google.gson.annotations.SerializedName

data class RedirectBundle(@SerializedName("redirects") var redirects: List<Redirect>)
