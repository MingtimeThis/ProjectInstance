package com.lead.projectinstance.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lead.projectinstance.R;
import com.lead.projectinstance.activity.Demo2Activity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/6.
 */

public class Demo2Adapter extends BaseAdapter {
    private List<String> mDatas;
    private Context context;

    public Demo2Adapter(Context context, List<String> mdatas) {
        this.mDatas = mdatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.adapter_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.item.setText(mDatas.get(position));


        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.item)
        TextView item;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
