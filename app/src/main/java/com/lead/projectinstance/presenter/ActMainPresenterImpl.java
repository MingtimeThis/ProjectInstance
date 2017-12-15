package com.lead.projectinstance.presenter;

import com.lead.projectinstance.Application.MyApplication;
import com.lead.projectinstance.contract.IMainContract;

/**
 * Created by Administrator on 2017/12/1.
 */

public class ActMainPresenterImpl implements IMainContract.IMainPresenter {
    IMainContract.IMainView view;

    public ActMainPresenterImpl(IMainContract.IMainView view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void login() {
        MyApplication.putDatas("data", "数据2");
        view.jumpActivity("数据");
    }

}
