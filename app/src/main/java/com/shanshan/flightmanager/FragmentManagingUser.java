package com.shanshan.flightmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentManagingUser extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public FragmentManagingUser() {
        // Required empty public constructor
    }

    public static FragmentManagingUser newInstance(String param1, String param2) {
        FragmentManagingUser fragment = new FragmentManagingUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_managing_user, container, false);
        Button userAddBtn = (Button) view.findViewById(R.id.user_add);
        Button userDeleteBtn = (Button) view.findViewById(R.id.user_delete);

        userAddBtn.setOnClickListener(addUserDataOnClickListener);
        userDeleteBtn.setOnClickListener(deleteUserDataOnClickListener);
        return view;
    }

    View.OnClickListener addUserDataOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), ActivitySignUp.class));
        }
    };

    View.OnClickListener deleteUserDataOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), DialogUserDelete.class));
        }
    };

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
                            if(FlightManagerDB.getInstance(getActivity())
                                    .searchUser(mAddAccountEt.getText().toString()).getId()==null) {
                                Log.i("DATA-A", mAddAccountEt.getText().toString());
                                if(mAddPasswordEt.getText().toString().length() >=6 ){
                                    Log.i("DATA-P", mAddPasswordEt.getText().toString());
                                    if(mAddPasswordEt.getText().toString().equals(
                                            mAddPasswordConfirmEt.getText().toString())){
                                        Log.i("DATA-PC", mAddPasswordConfirmEt.getText().toString());
                                        UserDatas userDatas = new UserDatas();
                                        userDatas.setId(mAddAccountEt.getText().toString());
                                        userDatas.setPassword(mAddPasswordEt.getText().toString());
                                        userDatas.setSex(mAddGenderRdG.getCheckedRadioButtonId()
                                                == R.id.male ? "男":"女");
                                        userDatas.setName(mAddNameEt.getText().toString());
                                        userDatas.setAge(Integer.parseInt(mAddAgeEt.getText()
                                                .toString()));
                                        FlightManagerDB.getInstance(getActivity()).saveUser(userDatas);

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
