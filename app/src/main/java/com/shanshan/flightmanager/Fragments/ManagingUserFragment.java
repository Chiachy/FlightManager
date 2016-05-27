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


/*
    public void showAddDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment dialogFragment = new UserAddDialog();
        dialogFragment.show(fragmentManager, "dialog");
    }
*/

    /*public class UserAddDialog extends DialogFragment {

        private EditText mAddNameEt;
        private EditText mAddAccountEt;
        private RadioGroup mAddGenderRdG;
        private EditText mAddAgeEt;
        private EditText mAddPasswordEt;
        private EditText mAddPasswordConfirmEt;

        *//*@Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.dialog_user_add, container);

            mAddNameBtn = (EditText) view.findViewById(R.id.user_add_name);
            mAddAccountBtn = (EditText) view.findViewById(R.id.user_add_account);
            mAddGenderRdG = (RadioGroup) view.findViewById(R.id.user_radio_gender);
            mAddAgeBtn = (EditText) view.findViewById(R.id.user_add_age);
            mAddPasswordBtn = (EditText) view.findViewById(R.id.user_add_password);
            mAddPasswordConfirmBtn= (EditText) view.findViewById(R.id.user_add_password_comfirm);

            return view;
        }*//*

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_user_add,null);
            mAddNameEt = (EditText) view.findViewById(R.id.user_add_name);
            mAddAccountEt = (EditText) view.findViewById(R.id.user_add_account);
            mAddGenderRdG = (RadioGroup) view.findViewById(R.id.user_radio_gender);
            mAddAgeEt = (EditText) view.findViewById(R.id.user_add_age);
            mAddPasswordEt = (EditText) view.findViewById(R.id.user_add_password);
            mAddPasswordConfirmEt= (EditText) view.findViewById(R.id.user_add_password_comfirm);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.dialog_user_add, null))
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_user_add,null);
                            mAddNameEt = (EditText) view.findViewById(R.id.user_add_name);
                            mAddAccountEt = (EditText) view.findViewById(R.id.user_add_account);
                            mAddGenderRdG = (RadioGroup) view.findViewById(R.id.user_radio_gender);
                            mAddAgeEt = (EditText) view.findViewById(R.id.user_add_age);
                            mAddPasswordEt = (EditText) view.findViewById(R.id.user_add_password);
                            mAddPasswordConfirmEt= (EditText) view.findViewById(R.id.user_add_password_comfirm);
                            if(DataBaseModel.getInstance(getActivity())
                                    .searchUser(mAddAccountEt.getText().toString()).getId()==null) {
                                Log.i("DATA-A", mAddAccountEt.getText().toString());
                                if(mAddPasswordEt.getText().toString().length() >=6 ){
                                    Log.i("DATA-P", mAddPasswordEt.getText().toString());
                                    if(mAddPasswordEt.getText().toString().equals(
                                            mAddPasswordConfirmEt.getText().toString())){
                                        Log.i("DATA-PC", mAddPasswordConfirmEt.getText().toString());
                                        ManagerUserDatas userDatas = new ManagerUserDatas();
                                        userDatas.setId(mAddAccountEt.getText().toString());
                                        userDatas.setPassword(mAddPasswordEt.getText().toString());
                                        userDatas.setSex(mAddGenderRdG.getCheckedRadioButtonId()
                                                == R.id.male ? "男":"女");
                                        userDatas.setName(mAddNameEt.getText().toString());
                                        userDatas.setAge(Integer.parseInt(mAddAgeEt.getText()
                                                .toString()));
                                        DataBaseModel.getInstance(getActivity()).saveUser(userDatas);

                                        Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_LONG )
                                                .show();
                                    }else{
                                        Toast.makeText(getActivity(), "密码不一致", Toast.LENGTH_LONG )
                                                .show();
                                    }
                                }else{
                                    Toast.makeText(getActivity(), "密码长度应至少六位",
                                            Toast.LENGTH_LONG ).show();
                                }
                            }else{
                                Toast.makeText(getActivity(), "此用户已被注册", Toast.LENGTH_LONG )
                                        .show();
                            }
                        }
                    })
                    .setNegativeButton(R.string.dialog_negative_button,
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            UserAddDialog.this.getDialog().cancel();
                        }
                    });

            return builder.create();
        }
    }*/


}
