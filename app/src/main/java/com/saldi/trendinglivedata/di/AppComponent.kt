package com.saldi.trendinglivedata.di

import android.app.Application
import com.saldi.trendinglivedata.TrendingApplication
import com.saldi.trendinglivedata.di.builder.ActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton



@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class, ActivityBuilder::class, ActivityBuilder::class]
)
interface AppComponent : AndroidInjector<TrendingApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(trendingApplication: TrendingApplication)
}