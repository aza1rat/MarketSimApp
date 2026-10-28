package ru.aza1rat.marketsimapp.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.aza1rat.marketsimapp.core.network.client.KtorNetworkExecutor
import ru.aza1rat.marketsimapp.core.network.client.NetworkExecutor

@Module
@InstallIn(SingletonComponent::class)
interface NetworkExecutorModule {

    @Binds
    fun bindNetworkExecutor(impl: KtorNetworkExecutor): NetworkExecutor
}
