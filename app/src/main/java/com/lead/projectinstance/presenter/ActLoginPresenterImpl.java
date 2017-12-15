package com.lead.projectinstance.presenter;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.lead.projectinstance.Application.MyApplication;
import com.lead.projectinstance.contract.ILoginContract;

/**
 * Created by Administrator on 2017/12/1.
 */

public class ActLoginPresenterImpl implements ILoginContract.ILoginPresenter {

    ILoginContract.ILoginView view;
    EditText mActLoginEtPhone, mActLoginEtPwd;

    private boolean isPhone = false, isPwd = false;

    public ActLoginPresenterImpl(ILoginContract.ILoginView view) {
        this.view = view;
        view.setPresenter(this);
    }


    @Override
    public void initData() {
        mActLoginEtPhone = view.getmActHomeEtPhone();
        mActLoginEtPwd = view.getmActLoginEtPwd();
        String data = (String) MyApplication.getDatas("data", false);
        view.showMsg(data);
        initEvent();
    }

    private void initEvent() {
        mActLoginEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    isPhone = false;
                } else {
                    isPhone = true;
                    if (isPwd) {
                    }
                }
            }
        });

        mActLoginEtPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    isPwd = false;
                } else {
                    isPwd = true;
                    if (isPhone) {
                    }
                }
            }
        });


    }

    @Override
    public void login() {
        view.jumpActivity();
    }
}
