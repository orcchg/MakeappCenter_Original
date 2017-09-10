package com.orcchg.makeappcenter.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.orcchg.makeappcenter.app.di.application.ApplicationComponent
import com.orcchg.makeappcenter.app.di.application.ApplicationModule
import com.orcchg.makeappcenter.app.di.application.DaggerApplicationComponent
import com.orcchg.makeappcenter.app.view.base.BaseActivity
import com.orcchg.makeappcenter.app.view.base.BaseFragment
import com.orcchg.makeappcenter.data.di.local.LocalModule
import com.orcchg.makeappcenter.data.di.remote.CloudModule
import com.orcchg.makeappcenter.data.di.repository.DaggerRepositoryComponent
import com.orcchg.makeappcenter.data.di.repository.RepositoryComponent
import com.orcchg.makeappcenter.data.di.repository.RepositoryModule
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelComponent
import com.orcchg.makeappcenter.data.di.viewmodel.ViewModelModule
import com.squareup.leakcanary.LeakCanary
import io.fabric.sdk.android.Fabric
import net.danlew.android.joda.JodaTimeAndroid
import org.joda.time.DateTimeZone
import timber.log.Timber

open class MainApplication : Application() {

    private lateinit var repositoryComponent: RepositoryComponent
    private lateinit var viewModelComponent: ViewModelComponent

    /* Lifecycle */
    // --------------------------------------------------------------------------------------------
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        initializeLeakDetection()
        initializeLogger()  // Logger must be initialized to show logs at the very beginning
        initializeCrashlytics()
        initializeInjector()
        initializeJodaTime()
        Timber.i("Application onCreate")
    }

//    override fun onTrimMemory(level: Int) {
//        super.onTrimMemory(level)
//        repositoryComponent.cartRepository().onDestroy()
//        repositoryComponent.productRepository().onDestroy()
//    }

    /* Initialization */
    // --------------------------------------------------------------------------------------------
    private fun initializeCrashlytics() {
        val core = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()
        Fabric.with(this, Crashlytics.Builder().core(core).build())
    }

    private fun initializeInjector() {
        repositoryComponent = initRepositoryComponent()
//        repositoryComponent.cartRepository().onCreate()
//        repositoryComponent.productRepository().onCreate()

        viewModelComponent = initViewModelComponent(repositoryComponent)

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                if (activity is BaseActivity) {
                    activity.viewModelComponent = viewModelComponent
                }
                if (activity is FragmentActivity) {
                    activity.supportFragmentManager
                            .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                                override fun onFragmentPreCreated(fm: FragmentManager, fragment: Fragment, fragmentSavedInstanceState: Bundle?) {
                                    super.onFragmentPreCreated(fm, fragment, fragmentSavedInstanceState)
                                    if (fragment is BaseFragment) {
                                        fragment.viewModelComponent = viewModelComponent
                                    }
                                }
                            }, true)
                }
            }

            override fun onActivityStarted(activity: Activity) {}
            override fun onActivityResumed(activity: Activity) {}
            override fun onActivityPaused(activity: Activity) {}
            override fun onActivitySaveInstanceState(activity: Activity, savedInstanceState: Bundle) {}
            override fun onActivityStopped(activity: Activity) {}
            override fun onActivityDestroyed(activity: Activity) {}
        })
    }

    private fun initializeJodaTime() {
        JodaTimeAndroid.init(this)
        DateTimeZone.setDefault(DateTimeZone.UTC)
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this)
        }
    }

    private fun initializeLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return packageName + ":" + super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        } else {
            Timber.plant(CrashlyticsTree())
        }
    }

    /* Components */
    // --------------------------------------------------------------------------------------------
    private fun initAppComponent(): ApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

    private fun initRepositoryComponent(): RepositoryComponent = DaggerRepositoryComponent.builder()
            .cloudModule(CloudModule(applicationContext))
            .localModule(LocalModule(applicationContext))
            .repositoryModule(RepositoryModule())
            .build()

    private fun initViewModelComponent(repositoryComponent: RepositoryComponent): ViewModelComponent =
            repositoryComponent.plus(ViewModelModule())

    /* Crashlytics */
    // --------------------------------------------------------------------------------------------
    /**
     * {@see https://blog.xmartlabs.com/2015/07/09/Android-logging-with-Crashlytics-and-Timber/}

     * Comment: [Timber.Tree] only supplies the tag when it was explicitly set.
     * In most cases, tag will be null. If you want the tag to be extracted from the log,
     * you need to extend [Timber.DebugTree] instead.
     */
    inner class CrashlyticsTree : Timber.DebugTree() {

        override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
            if (priority == Log.VERBOSE) {
                return
            }

            Crashlytics.setInt("priority", priority)
            Crashlytics.setString("tag", tag)
            Crashlytics.setString("message", message)

            if (t == null) {
                Crashlytics.log(priority, tag, message)
            } else {
                Crashlytics.logException(t)
            }
        }
    }
}
