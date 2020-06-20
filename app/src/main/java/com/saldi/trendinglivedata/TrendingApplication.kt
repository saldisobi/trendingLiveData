package com.saldi.trendinglivedata

import com.saldi.trendinglivedata.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class TrendingApplication : DaggerApplication() {
    private val applicationInjector = DaggerAppComponent.builder().application(this).build()
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = applicationInjector
}