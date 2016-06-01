package com.shanshan.flightmanager.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.FlightManagerApplication;
import com.shanshan.flightmanager.Tools.ManagerOrderDatas;
import com.shanshan.flightmanager.Tools.ManagerUserDatas;
import com.shanshan.flightmanager.Tools.ToolsRecyclerViewDividerLine;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends Activity {

    final FlightManagerApplication application = (FlightManagerApplication) getApplication();

    private RecyclerView mRecyclerView;
    private OrderAdapter orderAdapter;
    private Button mButton;
    private Toolbar mToolbar;

    public static String id;
    private ManagerUserDatas managerUserDatas;
    private TextView name;
    private TextView userId;
    private TextView sex;
    private TextView age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        managerUserDatas = DataBaseModel.getInstance(this).searchUser(id);
        name = (TextView) findViewById(R.id.user_name);
        userId = (TextView) findViewById(R.id.user_id);
        sex = (TextView) findViewById(R.id.user_sex);
        age = (TextView) findViewById(R.id.user_age);
        name.setText(managerUserDatas.getName());
        if (managerUserDatas.getSex().equals("男")) {
            sex.setText("先生");
        } else {
            sex.setText("女士");
        }
        userId.setText(managerUserDatas.getId());
        age.setText(String.valueOf(managerUserDatas.getAge()));

        mButton = (Button) findViewById(R.id.exit_login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(application.getIsLogin()){
                    application.setIsLogin(false);
                    finish();
                }
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.user_details);

        //mAdapters = new recycleViewAdapter(this, );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL , false);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.setHasFixedSize(true);//提高性能

        orderAdapter = new OrderAdapter(this, DataBaseModel.getInstance(this)
                .searchOrderDatas(managerUserDatas.getId()));

        ToolsRecyclerViewDividerLine recycViewDividerLine =
                new ToolsRecyclerViewDividerLine(ToolsRecyclerViewDividerLine.HORIZONTAL);

        recycViewDividerLine.setSize(5);

        recycViewDividerLine.setColor(0xFFDDDDDD);

        mRecyclerView.addItemDecoration(recycViewDividerLine);

        mRecyclerView.setAdapter(orderAdapter);

        if (orderAdapter.getItemCount() != 0) {
            TextView textView = (TextView) findViewById(R.id.list_info);
            textView.setVisibility(View.GONE);
        }

        mToolbar = (Toolbar) findViewById(R.id.user_details_toolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
    }



    private class OrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<ManagerOrderDatas> orderDatasList = new ArrayList<>();
        private Context context;
        private LayoutInflater inflater;
        private int mPosition;

        public OrderAdapter(Context context, List<ManagerOrderDatas> datasList) {
            orderDatasList = datasList;
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHodler(inflater.inflate(R.layout.item_order_list, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHodler) holder).orderInfo.setText("000" + String.valueOf(orderDatasList.get(position).getId()));
            ((MyViewHodler) holder).flightNumber.setText(orderDatasList.get(position).getFlightNumber());
            mPosition = position;
        }

        @Override
        public int getItemCount() {
            return orderDatasList.size();
        }

        private class MyViewHodler extends RecyclerView.ViewHolder {

            TextView orderInfo;
            TextView flightNumber;

            public MyViewHodler(final View itemView) {
                super(itemView);
                orderInfo = (TextView) itemView.findViewById(R.id.orderInfo);
                flightNumber = (TextView) itemView.findViewById(R.id.flight_number);


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDeleteOrderDialog(orderDatasList.get(mPosition));
                    }
                });
            }

        }
    }

    public void showDeleteOrderDialog(ManagerOrderDatas mdatas) {
        ConfirmDeleteOrderDialogFragment confirmDeleteOrderDialogFragment =
                new ConfirmDeleteOrderDialogFragment(mdatas);
        confirmDeleteOrderDialogFragment.show(getFragmentManager(), "showDeleteOrderDialog");
    }

    public class ConfirmDeleteOrderDialogFragment extends DialogFragment {

        private ManagerOrderDatas mdatas;

        public ConfirmDeleteOrderDialogFragment(ManagerOrderDatas datas) {
            this.mdatas = datas;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(R.string.ConfirmDeleteDialogFragmentTitle)
                    .setPositiveButton(R.string.ConfirmOkay, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            DataBaseModel db = DataBaseModel.getInstance(UserDetailsActivity.this);
                            db.deleteOrderData(mdatas.getId());
                            dismiss();
                        }
                    })
                    .setNegativeButton(R.string.ConfirmCancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return builder.create();
        }
    }

}
