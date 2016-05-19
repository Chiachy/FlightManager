package com.shanshan.flightmanager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.Fragments.ChooseWhereFromFragment;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.ToolClassies.ManagerFlightDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiachi on 2016/5/18.
 */
public class AdapterChooseWhereFromTo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static String TEXTVIEWNAME_WHEREFROM = "mWhereFrom";
    private final static String TEXTVIEWNAME_WHERETO = "mWhereTo";

    private LayoutInflater mInflater;
    private Context mContext;
    public static List<ManagerFlightDatas> mDatas = new ArrayList<>();
    private String mWhereText;
    private String mWhichTextView;
    private TextView mWhereTV;
    private ChooseWhereFromFragment fromFragment;

    //AdapterRVWhereFrom类的构造方法，用来获取上下文及航班数据
    public AdapterChooseWhereFromTo(Context context, List<ManagerFlightDatas> mDatas,
                                    String whichTextView, TextView whereTV, ChooseWhereFromFragment fg) {
        this.mContext = context;
        this.mDatas = mDatas;
        this.mWhichTextView = whichTextView;
        this.mWhereTV = whereTV;
        this.fromFragment = fg;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_where_from, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (mWhichTextView) {
            case TEXTVIEWNAME_WHEREFROM:
                ((MyViewHolder) holder).whereText.setText(mDatas.get(position).getWhereFrom());
                break;
            case TEXTVIEWNAME_WHERETO:
                ((MyViewHolder) holder).whereText.setText(mDatas.get(position).getWhereTo());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView whereText;

        public MyViewHolder(final View itemView) {
            super(itemView);

            whereText = (TextView) itemView.findViewById(R.id.item_textview_wherefrom);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWhereText = (String) whereText.getText();
                    mWhereTV.setText(mWhereText);
                    //Toast.makeText(mContext, mWhereText, Toast.LENGTH_SHORT).show();
                    fromFragment.dismiss();
                }
            });
        }
    }
}