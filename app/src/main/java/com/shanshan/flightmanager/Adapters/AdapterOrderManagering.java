package com.shanshan.flightmanager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.ToolClassies.ManagerOrderDatas;
import com.shanshan.flightmanager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiachi on 2016/4/20.
 */
public class AdapterOrderManagering extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    public static List<ManagerOrderDatas> mDatas = new ArrayList<>();

    public AdapterOrderManagering(Context context, List<ManagerOrderDatas> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order_layout_item , parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).orderNumber.setText( mDatas.get(position).getId());
        ((MyViewHolder)holder).userNumber.setText( mDatas.get(position).getUserId());
        ((MyViewHolder)holder).flightNumber.setText( mDatas.get(position).getFlight_number());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView orderNumber;
        TextView userNumber;
        TextView flightNumber;

        public MyViewHolder(final View itemView) {
            super(itemView);

            orderNumber = (TextView) itemView.findViewById(R.id.order_number);
            userNumber = (TextView) itemView.findViewById(R.id.user_number);
            flightNumber = (TextView) itemView.findViewById(R.id.flight_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("hang", "111111");
                }
            });

        }
    }
}
