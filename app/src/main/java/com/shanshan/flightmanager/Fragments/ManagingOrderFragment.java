package com.shanshan.flightmanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.Adapters.ManageOrderDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;


public class ManagingOrderFragment extends Fragment {

    private DataBaseModel mDataBase;
    private RecyclerView mRecyclerView;
    private TextView mTextView;
    private ManageOrderDatasAdapter mManageOrderDatasAdapter;

    public static ManagingOrderFragment newInstance(String param1, String param2) {
        ManagingOrderFragment fragment = new ManagingOrderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBase = DataBaseModel.getInstance(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_managing, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_order_managing);
        mTextView = (TextView) view.findViewById(R.id.order_tips);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false
        ));

        //if (mManageOrderDatasAdapter.getItemCount() != 0) {
        mTextView.setVisibility(View.GONE);
        //}

        mManageOrderDatasAdapter = new ManageOrderDatasAdapter(
                getActivity(), mDataBase.loadOrderDatas()
        );

        mRecyclerView.setAdapter(mManageOrderDatasAdapter);

        ToolsRecyclerViewDividerLine rVDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        rVDividerLine.setSize(15);
        rVDividerLine.setColor(0XFFDDDDDD);
        mRecyclerView.addItemDecoration(rVDividerLine);


        return view;
    }


}
