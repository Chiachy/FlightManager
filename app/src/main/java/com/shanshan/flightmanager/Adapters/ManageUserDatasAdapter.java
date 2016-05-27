package com.shanshan.flightmanager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.ManagerUserDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiachi on 2016/5/23.
 */
public class ManageUserDatasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "ManagerUserDatas?";

    private LayoutInflater mInflater;
    private Context mContext;
    public static List<ManagerUserDatas> mDatas = new ArrayList<>();
    private int mPosition;

    public ManageUserDatasAdapter(Context context, List<ManagerUserDatas> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_user_data, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).userId.setText(mDatas.get(position).getId());
        ((MyViewHolder) holder).userName.setText(mDatas.get(position).getName());
        ((MyViewHolder) holder).itemView.setTag(position);
        mPosition = position;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userId;
        TextView userName;

        public MyViewHolder(final View itemView) {
            super(itemView);

            userId = (TextView) itemView.findViewById(R.id.item_user_data_id);
            userName = (TextView) itemView.findViewById(R.id.item_user_data_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

    }
}
