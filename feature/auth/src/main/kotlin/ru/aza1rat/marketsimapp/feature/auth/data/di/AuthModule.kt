package ru.aza1rat.marketsimapp.feature.auth.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.core.network.client.NetworkClient
import ru.aza1rat.marketsimapp.feature.auth.data.api.AuthApi
import ru.aza1rat.marketsimapp.feature.auth.data.api.AuthApiImpl
import ru.aza1rat.marketsimapp.feature.auth.data.client.AuthNetworkClient

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun bindAuthApi(impl: AuthApiImpl): AuthApi

    @Binds
    fun bindAuthNetworkClient(impl: AuthNetworkClient): NetworkClient
}
