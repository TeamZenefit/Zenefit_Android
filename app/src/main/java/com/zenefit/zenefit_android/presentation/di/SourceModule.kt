package com.zenefit.zenefit_android.presentation.di

import com.zenefit.zenefit_android.data.remote.source.AuthRemoteSource
import com.zenefit.zenefit_android.data.remote.source.UserRemoteSource
import com.zenefit.zenefit_android.domain.source.AuthSource
import com.zenefit.zenefit_android.domain.source.UserSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindAuthRemoteSource(authRemoteSource: AuthRemoteSource) : AuthSource

    @Binds
    abstract fun bindUserRemoteSource(userRemoteSource: UserRemoteSource) : UserSource
}