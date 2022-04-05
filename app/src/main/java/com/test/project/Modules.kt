package com.test.project

import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.dataSource.provideSibgutiHerokuService
import com.test.project.data.remote.network.INetwork
import com.test.project.data.remote.network.Network
import com.test.project.data.remote.network.SupportInterceptor
import com.test.project.data.repo.SibgutiHerokuRepo
import com.test.project.domain.repo.ISibgutiHerokuRepo
import com.test.project.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    single { SupportInterceptor() }
    single<INetwork> { Network(get()) }
    single { provideSibgutiHerokuService(get()) }
}

val prefModule = module {
}

val remoteModule = module {
    single { SibgutiHerokuRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<ISibgutiHerokuRepo> { SibgutiHerokuRepo(get()) }
}

val viewModelModules = module {
    viewModel { ProfileViewModel(get()) }
}

fun getModules(): List<Module> {
    return listOf(networkModule, prefModule, remoteModule, repositoryModule, viewModelModules)
}