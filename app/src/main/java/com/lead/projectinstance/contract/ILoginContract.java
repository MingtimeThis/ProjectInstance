package com.lead.projectinstance.contract;

import android.widget.EditText;

import com.lead.projectinstance.base.BasePresenter;
import com.lead.projectinstance.base.BaseView;

/**
 * Created by Administrator on 2017/12/1.
 */

public class ILoginContract {
    public interface ILoginView extends BaseView<ILoginPresenter> {
        EditText getmActLoginEtPwd();

        EditText getmActHomeEtPhone();

        /**
         * Toast数据
         * @param msg
         */
        void showMsg(String msg);

        /**
         * 展示一个进度条对话框
         * @param title 标题
         * @param msg 显示的内容
         * @param flag 是否可以取消
         */
        void showLoadingDialog(String title, String msg, boolean flag);

        /**
         * 取消进度条
         */
        void canelLoadingDialog();

        /**
         * activity的跳转
         */
        void jumpActivity();
    }
    public interface ILoginPresenter extends BasePresenter<ILoginView> {
        /**
         * 登陆操作
         */
        void login();
    }

}
