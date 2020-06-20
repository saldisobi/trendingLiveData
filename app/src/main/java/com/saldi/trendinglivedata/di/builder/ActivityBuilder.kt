package com.saldi.trendinglivedata.di.builder

import com.saldi.trendinglivedata.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainActivityFragmentBuilder::class])
    abstract fun contributeMainActivity(): MainActivity
}