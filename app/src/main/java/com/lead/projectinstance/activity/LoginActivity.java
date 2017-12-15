package com.lead.projectinstance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lead.projectinstance.R;
import com.lead.projectinstance.base.BaseActivity;
import com.lead.projectinstance.contract.ILoginContract;
import com.lead.projectinstance.presenter.ActLoginPresenterImpl;
import com.lead.projectinstance.utils.LogUtils;
import com.lead.projectinstance.utils.ToastFactory;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView {


    @BindView(R.id.act_login_et_phone)
    EditText mActLoginEtPhone;
    @BindView(R.id.act_login_et_pwd)
    EditText mActLoginEtPwd;

    @BindView(R.id.tv_title_name)
    TextView mTitleName;
    @BindView(R.id.iv_title_back)
    ImageView mTitleBack;

    @BindView(R.id.act_login_btn_submit)
    Button act_login_btn_submit;


    private ILoginContract.ILoginPresenter presenter;


    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initAllMembersView(@Nullable Bundle savedInstanceState) {
        new ActLoginPresenterImpl(this);
        presenter.initData();
        mTitleName.setText("登录");
    }

    @OnClick({R.id.iv_title_back, R.id.act_login_btn_submit})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_back:
                finish();
                break;

            case R.id.act_login_btn_submit:
                presenter.login();
                break;
        }
    }

    @Override
    public void setPresenter(ILoginContract.ILoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public EditText getmActLoginEtPwd() {
        return mActLoginEtPwd;
    }

    @Override
    public EditText getmActHomeEtPhone() {
        return mActLoginEtPhone;
    }

    @Override
    public void showMsg(String msg) {
        ToastFactory.getToast(this, msg).show();
    }

    @Override
    public void showLoadingDialog(String title, String msg, boolean flag) {
        super.showProcessDialog(title, msg, flag);
    }

    @Override
    public void canelLoadingDialog() {
        super.dismissProcessDialog();
    }

    @Override
    public void jumpActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
