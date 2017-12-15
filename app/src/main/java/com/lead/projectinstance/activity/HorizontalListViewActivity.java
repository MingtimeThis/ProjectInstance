package com.lead.projectinstance.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.lead.projectinstance.R;
import com.lead.projectinstance.adapter.CustomArrayAdapter;
import com.lead.projectinstance.base.BaseActivity;
import com.lead.projectinstance.bean.CustomData;
import com.lead.projectinstance.widget.HorizontalListView;

public class HorizontalListViewActivity extends BaseActivity {

    private HorizontalListView mHlvSimpleList;
    private HorizontalListView mHlvCustomList;
    private HorizontalListView mHlvCustomListWithDividerAndFadingEdge;

    private String[] mSimpleListValues = new String[] { "Android", "iPhone", "WindowsMobile",
            "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
            "Linux", "OS/2" };

    private CustomData[] mCustomData = new CustomData[] {
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray"),
            new CustomData(Color.RED, "Red"),
            new CustomData(Color.WHITE, "White"),
            new CustomData(Color.BLACK, "Black"),
            new CustomData(Color.CYAN, "Cyan"),
            new CustomData(Color.DKGRAY, "Dark Gray"),
            new CustomData(Color.GREEN, "Green"),
            new CustomData(Color.LTGRAY, "Light Gray")};

    @Override
    public int getContentView() {
        return R.layout.activity_horizontal_list_view;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        mHlvSimpleList = (HorizontalListView) findViewById(R.id.hlvSimpleList);
        mHlvCustomList = (HorizontalListView) findViewById(R.id.hlvCustomList);
        mHlvCustomListWithDividerAndFadingEdge = (HorizontalListView) findViewById(R.id.hlvCustomListWithDividerAndFadingEdge);

        setupSimpleList();
        setupCustomLists();
    }

    private void setupSimpleList() {
        // Make an array adapter using the built in android layout to render a list of strings
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, mSimpleListValues);

        // Assign adapter to the HorizontalListView
        mHlvSimpleList.setAdapter(adapter);
    }

    private void setupCustomLists() {
        // Make an array adapter using the built in android layout to render a list of strings
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, mCustomData);

        // Assign adapter to HorizontalListView
        mHlvCustomList.setAdapter(adapter);
        mHlvCustomListWithDividerAndFadingEdge.setAdapter(adapter);
    }


}
