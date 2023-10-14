package com.zenefit.zenefit_android.presentation.di

import com.zenefit.zenefit_android.data.remote.repository.AuthRepositoryImpl
import com.zenefit.zenefit_android.data.remote.repository.UserRepositoryImpl
import com.zenefit.zenefit_android.domain.repository.AuthRepository
import com.zenefit.zenefit_android.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl : UserRepositoryImpl) : UserRepository
}