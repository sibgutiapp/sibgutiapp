package com.test.project

import com.test.project.data.dataSource.SibgutiHerokuRemoteDataSource
import com.test.project.data.dataSource.database.NewsDatabase
import com.test.project.data.dataSource.provideSibgutiHerokuService
import com.test.project.data.remote.network.INetwork
import com.test.project.data.remote.network.Network
import com.test.project.data.remote.network.SupportInterceptor
import com.test.project.data.repo.NewsRepo
import com.test.project.data.repo.ProfileRepo
import com.test.project.domain.repo.INewsRepo
import com.test.project.domain.repo.IProfileRepo
import com.test.project.ui.home.HomeViewModel
import com.test.project.ui.profile.ProfileViewModel
import com.test.project.ui.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
    single { SupportInterceptor() }
    single<INetwork> { Network(get()) }
    single { provideSibgutiHerokuService(get()) }
    single { NewsDatabase.getDatabase(get()) }
}

val prefModule = module {
}

val remoteModule = module {
    single { SibgutiHerokuRemoteDataSource(get()) }
}

val repositoryModule = module {
    single<IProfileRepo> { ProfileRepo(get()) }
    single<INewsRepo> { NewsRepo(get(),get()) }
}

val viewModelModules = module {
    viewModel { ProfileViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ScheduleViewModel() }
}

fun getModules(): List<Module> {
    return listOf(networkModule, prefModule, remoteModule, repositoryModule, viewModelModules)
}