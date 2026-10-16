package ru.aza1rat.marketsimapp.feature.auth.login.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.feature.auth.data.AuthRepositoryImpl
import ru.aza1rat.marketsimapp.feature.auth.domain.AuthRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
