package com.saldi.trendinglivedata.di.builder

import com.saldi.trendinglivedata.ui.detail.SecondFragment
import com.saldi.trendinglivedata.ui.list.FirstFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityFragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): FirstFragment

    @ContributesAndroidInjector
    abstract fun contributeSecondFragment(): SecondFragment
}