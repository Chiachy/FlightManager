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
            startActivity(new Intent(getActivity(), DialogUpdateFlightData.class));
        }
    };

    View.OnClickListener deleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(getActivity(), DialogFlightDelete.class));
        }
    };

}

