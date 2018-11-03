package com.example.askhat.decathlon.auth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.askhat.decathlon.R
import org.koin.android.ext.android.inject

class LoginActivity() : AppCompatActivity(),LoginContract.LoginView {
    override val presenter: LoginContract.LoginPresenter by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)
    }
}
