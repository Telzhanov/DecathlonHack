package com.example.askhat.decathlon.auth

import com.example.askhat.decathlon.core.util.BasePresenter
import com.example.askhat.decathlon.core.util.IPresenter
import org.koin.log.Logger

class LoginPresenter(private val repository: LoginContract.LoginRepository): LoginContract.LoginPresenter,
                    BasePresenter<LoginContract.LoginView>(){
    override fun viewIsReady() {

    }

    override fun destroy() {

    }

}