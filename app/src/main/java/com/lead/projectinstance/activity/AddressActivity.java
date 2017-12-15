package com.lead.projectinstance.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.lead.projectinstance.R;
import com.lead.projectinstance.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AddressActivity extends BaseActivity {


    @BindView(R.id.mRecyclerView)
    RecyclerView recyclerView;

    @Override
    public int getContentView() {
        return R.layout.activity_address;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        final VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
    }

    @OnClick({})
    public void onclick(View v) {

    }

}
