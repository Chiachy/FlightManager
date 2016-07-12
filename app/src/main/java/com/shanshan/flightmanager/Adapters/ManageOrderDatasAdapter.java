package com.shanshan.flightmanager.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.shanshan.flightmanager.R;
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
    private EditText mInputOrderIdEdTxt;


    public ManageOrderDatasAdapter(Context context, List<ManagerOrderDatas> mDatas, EditText mInputOrderIdEdTxt) {
        this.mInputOrderIdEdTxt = mInputOrderIdEdTxt;
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

        ((MyViewHolder) holder).orderNumber.setText(String.valueOf(mDatas.get(position).getId()));
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
                    String newTag = orderNumber.getText().toString();
                    mInputOrderIdEdTxt.setText("");
                    mInputOrderIdEdTxt.setText(newTag);
                    //Toast.makeText(mContext, "已将该订单号粘贴至下方输入框", Toast.LENGTH_SHORT).show();
                }
            });
        }

        }

        /*public void showDeleteOrderDialog(int mdatas) {
            ManagerUIActivity managerUIActivity = new ManagerUIActivity();
            FragmentManager manager = managerUIActivity.getSupportFragmentManager();

            ConfirmDeleteOrderDialogFragment confirmDeleteOrderDialogFragment =
                    new ConfirmDeleteOrderDialogFragment(mdatas);
            //confirmDeleteOrderDialogFragment.show();
        }

        public class ConfirmDeleteOrderDialogFragment extends DialogFragment {

            private int mOrderID;

            public ConfirmDeleteOrderDialogFragment(int orderId) {
                this.mOrderID = orderId;
            }

            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.ConfirmDeleteOrderDialogFragmentTitle)
                        .setPositiveButton(R.string.ConfirmOkay, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                DataBaseModel db = DataBaseModel.getInstance(getActivity());
                                db.deleteOrderData(mOrderID);
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
        }*/

}
