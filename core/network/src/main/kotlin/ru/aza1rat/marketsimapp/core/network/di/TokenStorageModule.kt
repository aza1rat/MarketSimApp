package ru.aza1rat.marketsimapp.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.core.network.TokenStorage
import ru.aza1rat.marketsimapp.core.network.TokenStorageImpl

@Module
@InstallIn(SingletonComponent::class)
interface TokenStorageModule {

    @Binds
    fun bindTokenStorage(impl: TokenStorageImpl): TokenStorage
}
