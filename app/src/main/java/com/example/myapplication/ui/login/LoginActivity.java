package com.example.myapplication.ui.login;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.base.BaseActivity;
import com.example.myapplication.common.Constant;
import com.example.myapplication.interfaces.login.LoginConstract;
import com.example.myapplication.models.bean.UserBean;
import com.example.myapplication.persenter.login.LoginPersenter;
import com.example.myapplication.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginConstract.Persenter> implements LoginConstract.View {
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected LoginConstract.Persenter createPersenter() {
        return new LoginPersenter();
    }

    @Override
    public void loginReturn(UserBean result) {
        SpUtils.getInstance().setValue("token",result.getData().getToken());
        finish();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        String nickname = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        persenter.login(nickname,password);
    }
}
