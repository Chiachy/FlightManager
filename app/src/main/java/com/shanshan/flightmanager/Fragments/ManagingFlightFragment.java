package com.shanshan.flightmanager.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.shanshan.flightmanager.Activities.ManageFlightAddActivity;
import com.shanshan.flightmanager.Adapters.ManageFlightDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;

public class ManagingFlightFragment extends Fragment {

    private static final String ARG_PARAM1 = "someInt";
    private static final String ARG_PARAM2 = "sometitle";
    private static final String TAG = "RecyclerViewFragment";

    private DataBaseModel mDataBase;
    private RecyclerView mRecView;
    private ManageFlightDatasAdapter mManageFlightDatasAdapter;
    private TextView mTipTV;

    public static ManagingFlightFragment newInstance(int param1, String param2) {
        ManagingFlightFragment managingFlightFragment = new ManagingFlightFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        managingFlightFragment.setArguments(args);
        return managingFlightFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBase = DataBaseModel.getInstance(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_managing_flight, container, false);

        mRecView = (RecyclerView) view.findViewById(R.id.rec_flight_managing);
        mRecView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false
        ));

        mManageFlightDatasAdapter = new ManageFlightDatasAdapter(
                getActivity(), mDataBase.loadFlightDatas()
        );

        if (mManageFlightDatasAdapter.getItemCount() != 0) {
            mTipTV = (TextView) view.findViewById(R.id.textView16);
            mTipTV.setVisibility(View.GONE);
        }

        mRecView.setAdapter(mManageFlightDatasAdapter);

        ToolsRecyclerViewDividerLine rVDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        rVDividerLine.setSize(15);
        rVDividerLine.setColor(0XFFDDDDDD);
        mRecView.addItemDecoration(rVDividerLine);

        Button addFlgihtBtn = (Button) view.findViewById(R.id.button_add);

        addFlgihtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ManageFlightAddActivity.class));
            }
        });

        return view;
    }

}

