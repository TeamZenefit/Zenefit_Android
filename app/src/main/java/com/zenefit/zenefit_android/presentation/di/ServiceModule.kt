package com.zenefit.zenefit_android.presentation.di

import com.zenefit.zenefit_android.data.remote.service.AuthService
import com.zenefit.zenefit_android.data.remote.service.PolicyService
import com.zenefit.zenefit_android.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    @Singleton
    fun provideAuthService(retrofit : Retrofit) : AuthService = retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit) : UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun providePolicyService(retrofit: Retrofit) : PolicyService = retrofit.create(PolicyService::class.java)
}