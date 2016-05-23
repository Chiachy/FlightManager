package com.shanshan.flightmanager.Adapters;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.Tools.DataBaseModel;
import com.shanshan.flightmanager.Tools.ManagerOrderDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chiachi on 2016/5/23.
 */
public class ManageOrderDatasAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    public static List<ManagerOrderDatas> mDatas = new ArrayList<>();
    private int mPosition;

    public ManageOrderDatasAdapter(Context context, List<ManagerOrderDatas> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_order_layout_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).orderNumber.setText(mDatas.get(position).getId());
        ((MyViewHolder) holder).userNumber.setText(mDatas.get(position).getUserId());
        ((MyViewHolder) holder).flightNumber.setText(mDatas.get(position).getFlightNumber());
        ((MyViewHolder) holder).itemView.setTag(position);
        mPosition = position;
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
                    showDeleteOrderDialog(mDatas.get(mPosition));
                }
            });

        }

    }

    public void showDeleteOrderDialog(ManagerOrderDatas mdatas) {
        ConfirmDeleteOrderDialogFragment confirmDeleteOrderDialogFragment =
                new ConfirmDeleteOrderDialogFragment(mdatas);
        confirmDeleteOrderDialogFragment.show(confirmDeleteOrderDialogFragment.getFragmentManager(), "showDeleteOrderDialog");
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
                            DataBaseModel db = DataBaseModel.getInstance(getActivity());
                            db.deleteOrderData(String.valueOf(mdatas.getId()));
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
