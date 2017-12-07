package com.example.a15096.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class deviceSetActivity extends AppCompatActivity {
    private ListView list_one;
    private MyAdapter mAdapter = null;
    private List<Data> mData = null;
    private Context mContext = null;
    private Button btn_add;
    private Button btn_remove;
    private int flag = 1;
    private Data mData_5 = null;   //用来临时放对象的
    private final static String PREFRENCE_FILE_KEY = "com.example.a15096.shared_preferences";
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_set);
        mContext = deviceSetActivity.this;
        mSharedPreferences=getSharedPreferences(PREFRENCE_FILE_KEY, Context.MODE_PRIVATE);
        bindViews();
        mData = new LinkedList<Data>();
        mAdapter = new MyAdapter((LinkedList<Data>) mData,mContext);
        list_one.setAdapter(mAdapter);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_remove = (Button) findViewById(R.id.btn_remove);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adddeviceSet();
            }
        });
        btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removedeviceSet(view);
            }
        });

        Button button_DeviceSetButton = (Button) findViewById(R.id.buttonDeviceSet);
        button_DeviceSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceSetPage();
            }
        });

        Button buttonDeviceControltButton = (Button) findViewById(R.id.buttonDeviceControl);
        buttonDeviceControltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deviceControlPage();
            }
        });

        if(!mSharedPreferences.getAll().isEmpty())
        {
            Map<String, ?> map = mSharedPreferences.getAll();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                mData_5 = new Data(R.mipmap.ic_launcher,key + flag);
                mAdapter.add(mData_5);
                flag++;
            }
        }
    }

    /**
     * Device Set page
     */
    private void deviceSetPage()
    {
        Intent intent=new Intent(this,deviceSetActivity.class);
        startActivity(intent);
    }

    /**
     * Device Control page
     */
    private void deviceControlPage()
    {
        Intent intent=new Intent(this,controlDeviceActivity.class);
        startActivity(intent);
    }

    private void adddeviceSet()
    {
        mData_5 = new Data(R.mipmap.ic_launcher,"给希哥跪了~~~ x " + flag);
        mAdapter.add(mData_5);
        flag++;
       final SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(mData_5.getContent(),"");
        //editor.putInt("age",24);
        editor.apply();

    }
    private void removedeviceSet(View v)
    {
        if(v.getId()==R.id.btn_remove)
        {
            mAdapter.remove(mData_5);
        }
    }
    private void bindViews(){
        list_one = (ListView) findViewById(R.id.list_one);
    }
}