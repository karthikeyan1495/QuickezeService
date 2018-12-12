package com.star.quickezeservice.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.star.quickezeservice.R;
import com.star.quickezeservice.databinding.ActivityMainBinding;
import com.star.quickezeservice.login.modal.Login;
import com.star.quickezeservice.login.viewmodal.LoginVm;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    LoginVm loginVm;
    Login login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        login=new Login();
        loginVm=new LoginVm(this,binding,login);
        binding.setLoginVm(loginVm);
        binding.setLogin(login);


    }
}
