package com.orcchg.makeappcenter.app.navigation

import android.support.v4.app.Fragment
import com.ncapdevi.fragnav.FragNavController

/**
 * Complementary interface for [FragNavController], allowing to manipulate with current [Fragment]'s
 * stack for navigation purposes.
 */
interface FragmentNavigationHandler {
    fun pushFragment(fragment: Fragment)
}
