package com.test.project

import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule = module {
}

val prefModule = module {
}

val remoteModule = module {
}

val repositoryModule = module {
}

val viewModelModules = module {
}

fun getModules(): List<Module> {
    return listOf(networkModule, prefModule, remoteModule, repositoryModule, viewModelModules)
}