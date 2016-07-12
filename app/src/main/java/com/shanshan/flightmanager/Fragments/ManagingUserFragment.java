package com.shanshan.flightmanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.Adapters.ManageUserDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;


public class ManagingUserFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private DataBaseModel mDataBase;
    private ManageUserDatasAdapter mManageUserDatasAdapter;
    private TextView mTipTV;

    public static ManagingUserFragment newInstance(String param1, String param2) {
        ManagingUserFragment fragment = new ManagingUserFragment();
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
        View view = inflater.inflate(R.layout.fragment_managing_user, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_user_managing);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false
        ));

        mManageUserDatasAdapter = new ManageUserDatasAdapter(getActivity(), mDataBase.loadUserDatas());
        mRecyclerView.setAdapter(mManageUserDatasAdapter);

        if (mManageUserDatasAdapter.getItemCount() != 0) {
            mTipTV = (TextView) view.findViewById(R.id.manage_user_tip);
            mTipTV.setVisibility(View.GONE);
        }

        ToolsRecyclerViewDividerLine rVDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        rVDividerLine.setSize(15);
        rVDividerLine.setColor(0XFFDDDDDD);
        mRecyclerView.addItemDecoration(rVDividerLine);

        return view;
    }

}
