package com.shanshan.flightmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentManagingFlight extends Fragment {

    private static final String ARG_PARAM1 = "someInt";
    private static final String ARG_PARAM2 = "sometitle";

    private int page;
    private String title;

    public FragmentManagingFlight() {
        // Required empty public constructor
    }

    public static FragmentManagingFlight newInstance(int param1, String param2) {
        FragmentManagingFlight fragmentManagingFlight = new FragmentManagingFlight();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragmentManagingFlight.setArguments(args);
        return fragmentManagingFlight;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            page = getArguments().getInt(ARG_PARAM1, 0);
            title = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_managing_flight, container, false);
        Button addFlgihtBtn = (Button) view.findViewById(R.id.button_add);
        Button updateFlightBtn = (Button) view.findViewById(R.id.update_flight);
        Button deleteFlgihtBtn = (Button) view.findViewById(R.id.delete_flight);

        addFlgihtBtn.setOnClickListener(addOnClickListener);
        updateFlightBtn.setOnClickListener(updateOnClickListener);
        deleteFlgihtBtn.setOnClickListener(deleteOnClickListener);

        return view;
    }

    View.OnClickListener addOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), DialogFlightAdd.class));
        }
    };

    View.OnClickListener updateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };



    //dialog对话框
    /*public class FlightAddDialog extends DialogFragment {

        @NonNull //加了@NonNull后就显示出来了，棒！
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater inflater = getActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.dialog_flight_add, null))
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FlightAddDialog.this.getDialog().cancel();
                        }
                    });

            *//*Dialog dialog = super.onCreateDialog(savedInstanceState);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);*//*
            return builder.create();
        }
    }

    public class FlightUpdateDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater myInflater = getActivity().getLayoutInflater();

            builder.setView(myInflater.inflate(R.layout.dialog_flight_update,null))
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FlightUpdateDialog.this.getDialog().cancel();
                        }
                    });

            return builder.create();
        }
    }*/

    /*public class FlightDeleteDialog extends DialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            LayoutInflater myInflater = getActivity().getLayoutInflater();

            builder.setView(myInflater.inflate(R.layout.dialog_flight_delete,null))
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton(R.string.dialog_positive_button, new DialogInterface
                            .OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            FlightDeleteDialog.this.getDialog().cancel();
                        }
                    });


            return builder.create();
        }
    }

    public void showAddDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment dialogFragment = new FlightAddDialog();
        dialogFragment.show(fragmentManager, "dialog");
    }

    public void showUpdateDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment dialogFragment = new FlightUpdateDialog();
        dialogFragment.show(fragmentManager, "dialog");
    }

    public void showDeleteDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment dialogFragment = new FlightDeleteDialog();
        dialogFragment.show(fragmentManager, "dialog");
    }
*/

}

