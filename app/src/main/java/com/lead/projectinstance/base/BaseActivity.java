package com.lead.projectinstance.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.lead.projectinstance.Application.MyApplication;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/11/30.
 */

public abstract class BaseActivity extends FragmentActivity {
    //声明一个构建着对象，用于创建警告对话框
    private AlertDialog.Builder builder;
    //用于创建一个进度条对话框
    private ProgressDialog dialog;
    //用于打印log
    public final String TAG = "myTag";
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //固定屏幕方向
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(getContentView());
        bind = ButterKnife.bind(this);
        initAllMembersView(savedInstanceState);

        //添加Activity到堆栈
        MyApplication.getInstance().pushActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        MyApplication.getInstance().popActivity(this);
    }

    public abstract int getContentView();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    /**
     * 功能：显示一个警告对话框,无按钮，需要自己设置
     *
     * @param title 标题
     * @param msg   内容
     * @param flag  是否可以取消
     * @return AlertDialog.Builder 对象
     */
    protected AlertDialog.Builder showAlertDialog(String title, String msg, boolean flag) {
        if (builder == null) {
            //创建一个构建者对象
            builder = new AlertDialog.Builder(this);
            builder.setTitle(title).setMessage(msg).setCancelable(flag);
        }
        return builder;
    }

    /**
     * 功能:取消警告对话框
     */
    protected void dismissAlertDialog(AlertDialog alertDialog) {
        if (alertDialog != null) {
            //取消警告对话框
            alertDialog.dismiss();
        }
    }

    /**
     * 功能 ：显示一个进度条对话框
     */
    protected void showProcessDialog(String title, String msg, boolean falg) {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
        }
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(falg);
        dialog.show();
    }

    /**
     * 功能 ：取消一个进度条对话框
     */
    protected void dismissProcessDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
