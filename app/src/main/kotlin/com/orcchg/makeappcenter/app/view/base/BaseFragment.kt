package com.orcchg.makeappcenter.app.view.base

import android.support.v4.app.Fragment
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelComponent

abstract class BaseFragment : Fragment() {

    lateinit var viewModelComponent: ViewModelComponent
}
