package com.example.askhat.decathlon.auth

import com.example.askhat.decathlon.Constants
import com.example.askhat.decathlon.core.createService
import org.koin.dsl.module.module

val authModule = module {
    factory { LoginPresenter(get()) as LoginContract.LoginPresenter }
    factory { LoginRepository() as LoginContract.LoginRepository }
    single { createService<LoginService>(get(), Constants.URL) }
}