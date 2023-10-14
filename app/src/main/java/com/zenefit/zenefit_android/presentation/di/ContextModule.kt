package com.zenefit.zenefit_android.presentation.di

import android.content.Context
import com.zenefit.zenefit_android.data.local.repository.SharedPreferenceLocalRepositoryImpl
import com.zenefit.zenefit_android.data.local.source.SharedPreferenceLocalSource
import com.zenefit.zenefit_android.domain.repository.SharedPreferenceRepository
import com.zenefit.zenefit_android.domain.source.SharedPreferenceSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context : Context) : Context = context

    @Provides
    @Singleton
    fun provideSharedPrefSource() : SharedPreferenceSource = SharedPreferenceLocalSource()

    @Provides
    @Singleton
    fun provideSharedPrefRepo() : SharedPreferenceRepository = SharedPreferenceLocalRepositoryImpl(SharedPreferenceLocalSource())
}