package com.orcchg.makeappcenter.domain.model

import com.google.gson.annotations.SerializedName

data class WebPageBundle(@SerializedName("pages") var pages: List<WebPage>)
