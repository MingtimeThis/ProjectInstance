package com.lead.projectinstance.contract;

import com.lead.projectinstance.base.BasePresenter;
import com.lead.projectinstance.base.BaseView;

/**
 * Created by Administrator on 2017/12/1.
 */

public class IMainContract  {
    public interface IMainView extends BaseView<IMainPresenter>{
        void setMsg(String msg);

        void jumpActivity(String data);


    }
    public interface IMainPresenter extends BasePresenter<IMainView>{
        void login();
    }

}
