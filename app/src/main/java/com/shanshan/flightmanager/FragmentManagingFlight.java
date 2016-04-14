package com.shanshan.flightmanager;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentManagingFlight.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentManagingFlight#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentManagingFlight extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "someInt";
    private static final String ARG_PARAM2 = "sometitle";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int page;
    private String title;

    private OnFragmentInteractionListener mListener;

    public FragmentManagingFlight() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentManagingFlight.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentManagingFlight newInstance(int param1, String param2) {
        FragmentManagingFlight fragmentManagingFlight = new FragmentManagingFlight();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
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
        TextView textViewLabel = (TextView) view.findViewById(R.id.textViewLablef);
        textViewLabel.setText(page + "--" + title);
        return view;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
