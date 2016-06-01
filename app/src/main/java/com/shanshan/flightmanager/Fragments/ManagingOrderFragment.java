package com.shanshan.flightmanager.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shanshan.flightmanager.Adapters.ManageOrderDatasAdapter;
import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;


public class ManagingOrderFragment extends Fragment {

    private EditText mInputOrderIdEdTxt;
    private Button mConfirmDeleteBtn;

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

        initViews(view);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity(), LinearLayoutManager.VERTICAL, false
        ));
        mManageOrderDatasAdapter = new ManageOrderDatasAdapter(
                getActivity(), mDataBase.loadOrderDatas(), mInputOrderIdEdTxt
        );
        if (mManageOrderDatasAdapter.getItemCount() != 0) {
            mTextView.setVisibility(View.GONE);
        }
        mRecyclerView.setAdapter(mManageOrderDatasAdapter);

        ToolsRecyclerViewDividerLine HORIZONTALDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);
        HORIZONTALDividerLine.setSize(8);
        HORIZONTALDividerLine.setColor(0XFFDDDDDD);
        mRecyclerView.addItemDecoration(HORIZONTALDividerLine);

        mConfirmDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(mInputOrderIdEdTxt.getText().toString());
                mDataBase.deleteOrderData(id);
                Toast.makeText(getActivity(), "订单已删除，重新登录管理员后生效", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void initViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rec_order_managing);
        mTextView = (TextView) view.findViewById(R.id.order_tips);
        mInputOrderIdEdTxt = (EditText) view.findViewById(R.id.manage_order_delete_edtxt);
        mConfirmDeleteBtn = (Button) view.findViewById(R.id.manage_order_delete_confirm_btn);
    }

}
