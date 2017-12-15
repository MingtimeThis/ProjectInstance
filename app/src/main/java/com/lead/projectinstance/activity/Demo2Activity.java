package com.lead.projectinstance.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView;
import com.lead.projectinstance.R;
import com.lead.projectinstance.adapter.Demo2Adapter;
import com.lead.projectinstance.base.BaseActivity;
import com.lead.projectinstance.utils.ToolsUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Demo2Activity extends BaseActivity {
    @BindView(R.id.mPullView)
    PullToRefreshHorizontalScrollView mPullView;
//    @BindView(R.id.mListView)
//    HorizontalListView mListView;

    @BindView(R.id.mGridView)
    GridView mGridView;

    @BindView(R.id.mPullView2)
    PullToRefreshHorizontalScrollView mPullView2;
//    @BindView(R.id.mListView)
//    HorizontalListView mListView;

    @BindView(R.id.mGridView2)
    GridView mGridView2;


//    ListView mlist;

    private List<String> mDatas;

    @Override
    public int getContentView() {
        return R.layout.activity_demo2;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("数据" + i);
        }
        mPullView2.setGravity(Gravity.CENTER);
//        mPullView2.getLoadingLayoutProxy().setLoadingDrawable();
//        mPullView2.getLoadingLayoutProxy().setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        mPullView2.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        mPullView2.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<HorizontalScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<HorizontalScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
        mPullView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<HorizontalScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<HorizontalScrollView> refreshView) {
                new GetDataTask().execute();
            }
        });
        setGridViewWidth(mGridView, mDatas.size());
        setGridViewWidth(mGridView2, mDatas.size());
        Demo2Adapter adapter = new Demo2Adapter(this, mDatas);
        mGridView.setAdapter(adapter);
        mGridView2.setAdapter(adapter);
    }


    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Do some stuff here

            // Call onRefreshComplete when the list has been refreshed.
            mPullView.onRefreshComplete();
            mPullView2.onRefreshComplete();
            super.onPostExecute(result);
        }
    }

    public void setGridViewWidth(GridView view, int size) {
        int gridviewWidth = ToolsUtil.dip2px(this, 90 + 8) * size - ToolsUtil.dip2px(this, 8);
        int itemWidth = ToolsUtil.dip2px(this, 90);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params); //设置GirdView布局参数,横向布局的关键
        view.setColumnWidth(itemWidth);
        view.setHorizontalSpacing(ToolsUtil.dip2px(this, 8));
        view.setStretchMode(GridView.NO_STRETCH);
        view.setNumColumns(size);
    }
}
