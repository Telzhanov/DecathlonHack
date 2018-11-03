package com.example.askhat.decathlon.auth

import com.example.askhat.decathlon.core.util.IPresenter
import com.example.askhat.decathlon.core.util.IView

interface LoginContract{

    interface LoginView: IView<LoginPresenter>{

    }

    interface LoginPresenter:IPresenter<LoginView>{

    }

    interface LoginRepository{

    }
}