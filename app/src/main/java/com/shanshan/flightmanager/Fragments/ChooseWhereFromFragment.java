package com.shanshan.flightmanager.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.Adapters.AdapterChooseWhereFromTo;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.ToolClassies.ToolsRecyclerViewDividerLine;

public class ChooseWhereFromFragment extends DialogFragment {

    private static final String TAG_FRAGMENT = "ChooseWhereFromFragment";

    private AdapterChooseWhereFromTo mAdapter;
    private DataBaseModel db;
    private String mWhichTextView;
    private TextView mWhereText;
    private ChooseWhereFromFragment fromFragment;
    private FragmentTransaction fragmentTransaction;

    public ChooseWhereFromFragment(String name, TextView whereText) {
        this.mWhichTextView = name;
        this.mWhereText = whereText;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = DataBaseModel.getInstance(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_choose_wherefrom_list, container, false);

        View recyclerView = v.findViewById(R.id.id_choose_wherefrom_rv);

        fromFragment = this;

        //设置RecyclerView的适配器
        mAdapter = new AdapterChooseWhereFromTo(getActivity(), db.loadFlightDatas(),
                mWhichTextView, mWhereText, fromFragment);

        ((RecyclerView) recyclerView).setAdapter(mAdapter);

        //设置RecyclerView的布局管理器
        ((RecyclerView) recyclerView).setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //设置RecyclerView的分割线属性
        ToolsRecyclerViewDividerLine rVDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        rVDividerLine.setSize(5);
        rVDividerLine.setColor(0XFFDDDDDD);
        ((RecyclerView) recyclerView).addItemDecoration(rVDividerLine);

        return v;
    }
}
