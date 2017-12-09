package com.example.a15096.myapplication;

/**
 * Created by 15096 on 2017/12/9.
 */

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class ListItemAdapter extends BaseAdapter implements OnClickListener {
    private List<String> mList;
    private Context mContext;
    private InnerItemOnclickListener mListener;

    public ListItemAdapter(List<String> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        // TODO 自动生成的方法存根
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO 自动生成的方法存根
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO 自动生成的方法存根
        return position;
    }

    public void deleteItem(int position) {
        this.mList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,
                    null);
            viewHolder.switchlight = (Switch) convertView.findViewById(R.id.switchlight);
            viewHolder.deletedevice = (Button) convertView.findViewById(R.id.buttonDelete);
            viewHolder.deviceDecription = (TextView) convertView.findViewById(R.id.deviceDecription);
            viewHolder.status = (TextView) convertView.findViewById(R.id.status);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.switchlight.setOnClickListener(this);
        viewHolder.deletedevice.setOnClickListener(this);
        viewHolder.deviceDecription.setOnClickListener(this);
        viewHolder.status.setOnClickListener(this);
        viewHolder.switchlight.setTag(position);
        viewHolder.deletedevice.setTag(position);
        viewHolder.deviceDecription.setText(mList.get(position));
        return convertView;
    }

    public final static class ViewHolder {
        Button deletedevice;
        Switch switchlight;
        TextView deviceDecription;
        TextView status;
    }

    interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
