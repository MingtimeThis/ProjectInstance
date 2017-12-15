package com.lead.projectinstance.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lead.projectinstance.R;
import com.lead.projectinstance.base.BaseActivity;
import com.lead.projectinstance.contract.IMainContract;
import com.lead.projectinstance.http.NetWorks;
import com.lead.projectinstance.presenter.ActMainPresenterImpl;
import com.lead.projectinstance.utils.ToastFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observer;

import static android.R.attr.permission;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends BaseActivity implements IMainContract.IMainView {

    @BindView(R.id.act_home_btn_login)
    Button act_home_btn_login;
    @BindView(R.id.act_home_btn_relation)
    Button act_home_btn_relation;
    @BindView(R.id.act_home_tv_name)
    TextView act_home_tv_name;
    @BindView(R.id.act_home_tv_phone)
    TextView act_home_tv_phone;

    IMainContract.IMainPresenter presenter;

    private String[] PERMISSIONS_CONTACT = {Manifest.permission.READ_CONTACTS};
    private static final int REQUEST_CONTACTS = 101;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        new ActMainPresenterImpl(this);
        presenter.initData();
    }


    @OnClick({R.id.act_home_btn_login, R.id.act_home_btn_relation})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.act_home_btn_login:
                presenter.login();
                break;

            case R.id.act_home_btn_relation:
                setPermission();

                break;
        }
    }

    @Override
    public void setPresenter(IMainContract.IMainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setMsg(String msg) {
        ToastFactory.getToast(this, msg);
    }

    @Override
    public void jumpActivity(String data) {
        Intent intent = new Intent(this, Demo2Activity.class);
        intent.putExtra("data", data);
        startActivity(intent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            ContentResolver reContentResolverol = getContentResolver();
            Uri contactData = data.getData();
            @SuppressWarnings("deprecation")
            Cursor cursor = managedQuery(contactData, null, null, null, null);
            if (cursor != null && cursor.getCount() != 0) {
                cursor.moveToFirst();
                String username = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phone = reContentResolverol.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null,
                        null);
                act_home_tv_name.setText(username);
                String usernumber = "";
                while (phone.moveToNext()) {
                    usernumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                }
                act_home_tv_phone.setText(usernumber);
            }
        }
    }

    public void setPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                this.requestPermissions(PERMISSIONS_CONTACT, REQUEST_CONTACTS);
            } else {
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            }
        } else {
            startActivityForResult(new Intent(
                    Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            } else {
                //用户拒绝权限申请
                Toast.makeText(this, "请打开联系人权限", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 200) {
            // 如果权限被拒绝，grantResults 为空
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(new Intent(
                        Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            } else {
                Toast.makeText(this, "请打开联系人权限", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
