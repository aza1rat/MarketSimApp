package ru.aza1rat.marketsimapp.feature.auth.impl.login.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.feature.auth.impl.data.AuthRepositoryImpl
import ru.aza1rat.marketsimapp.feature.auth.impl.domain.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
