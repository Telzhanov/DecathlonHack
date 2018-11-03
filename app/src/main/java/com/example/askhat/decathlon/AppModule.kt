package com.example.askhat.decathlon

import com.example.askhat.decathlon.auth.authModule
import com.example.askhat.decathlon.core.coreModule
import com.example.askhat.decathlon.core.createService
import com.example.askhat.decathlon.core.util.Logger
import com.example.askhat.decathlon.store.StoreService
import org.koin.dsl.module.Module
import org.koin.dsl.module.module


val appModules: List<Module>
    get() = listOf(
            authModule,
            coreModule,
            singletons
    )

val singletons = module {
    Logger.msg("accepted",Constants.URL )
    single { createService<StoreService>(get(), Constants.URL) }
}