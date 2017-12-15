package com.lead.projectinstance.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Administrator on 2017/11/30.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    public Context context;
    public Stack<Activity> activityStack;
    //用于存放数据
    private static Map<String, Object> datas = new HashMap<String, Object>();

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static Object getDatas(String key, boolean delFlag) {
        if (delFlag) {
            return datas.remove(key);
        }
        return datas.get(key);
    }

    public static Object putDatas(String key, Object value) {
        return datas.put(key, value);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 退出栈顶Activity,并关闭
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activityStack.remove(activity);
                activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        Log.e("tag", "添加activity");
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 根据一个类名得到activity
     *
     * @param name
     * @return
     */
    public Activity getActivityByClassName(String name) {
        for (Activity actv : activityStack) {
            if (actv.getClass().getName().indexOf(name) >= 0) {
                return actv;
            }
        }
        return null;
    }


    /**
     * 获得当前栈顶Activity
     *
     * @return
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 退出栈中所有Activity
     */
    public void popAllActivity() {
        for (Activity actv : activityStack) {
            actv.finish();
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
        } catch (Exception e) {
        }
    }


    //移除除登录界面的其他界面
//    public void AppExitExceptLogin() {
//        for (int i = 0, size = activityStack.size(); i < size; i++) {
//            if (null != activityStack.get(i) && activityStack.get(i).getClass() != LoginActivity.class) {
//                activityStack.get(i).finish();
//            }
//        }
//    }
}
