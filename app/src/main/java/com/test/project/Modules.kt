package com.test.project

import com.test.project.data.dataSource.ISibgutiHerokuService
import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.remote.network.INetwork
import com.test.project.data.remote.network.Network
import com.test.project.data.remote.network.SupportInterceptor
import com.test.project.data.repo.NewsRepo
import com.test.project.data.repo.ProfileRepo
import com.test.project.domain.repo.INewsRepo
import com.test.project.domain.repo.IProfileRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideSupportInterceptor(): SupportInterceptor {
        return SupportInterceptor()
    }

    @Provides
    fun provideNetwork(supportInterceptor: SupportInterceptor): INetwork {
        return Network(supportInterceptor)
    }

    @Provides
    fun provideSibgutiHerokuService(network: INetwork): ISibgutiHerokuService {
        return network.retrofit.create(
            ISibgutiHerokuService::class.java
        )
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataSourceModule {

    @ActivityRetainedScoped
    fun provideSibgutiHerokuRemoteDataSource(api: ISibgutiHerokuService) {
        return provideSibgutiHerokuRemoteDataSource(api)
    }
}

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideProfileRepo(dataSource: SibgutiHerokuRemoteDataSource): IProfileRepo {
        return ProfileRepo(dataSource)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideNewsRepo(dataSource: SibgutiHerokuRemoteDataSource): INewsRepo {
        return NewsRepo(dataSource)
    }

}