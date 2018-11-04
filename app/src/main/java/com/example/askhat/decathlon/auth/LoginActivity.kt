package com.example.askhat.decathlon.auth

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.askhat.decathlon.R
import com.example.askhat.decathlon.menu.MainMenuActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity() : AppCompatActivity(),LoginContract.LoginView {
    override val presenter: LoginContract.LoginPresenter by inject()
    val service: LoginService by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.attachView(this)
        loginSignInButton.setOnClickListener { _ ->
            progressBar.visibility= View.VISIBLE
            loginSignInButton.text = ""
            service.authorize(loginEmailEditText.text.toString(),loginPasswordEditText.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (it.code ==0){
                        val user:User = it.user
                        val intent = Intent(this,MainMenuActivity::class.java)
                        intent.putExtra("user",user)
                        startActivity(intent)
                        finish()
                    }
                }
        }
    }
}
