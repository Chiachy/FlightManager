package com.shanshan.flightmanager.Fragments;

import android.content.Context;
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

import com.shanshan.flightmanager.R;
import com.shanshan.flightmanager.ToolClassies.DataBaseModel;
import com.shanshan.flightmanager.ToolClassies.ManagerFlightDatas;

import java.util.ArrayList;
import java.util.List;

public class FragmentManagingFlight extends Fragment {

    private static final String ARG_PARAM1 = "someInt";
    private static final String ARG_PARAM2 = "sometitle";
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 60;

    private RecyclerView mRecView;
    private RecyclerView.LayoutManager mLayoutManager;
    private AdapterManagerViewFlight managerViewFlight;
    protected String[] mDataset;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

//    public FragmentManagingFlight() {
//        // Required empty public constructor
//    }

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

        initDataset();

        AdapterManagerViewFlight managerViewFlight = new AdapterManagerViewFlight(getActivity(),
                DataBaseModel.getInstance(getActivity()).loadFlightDatas());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_managing_flight, container, false);
        view.setTag(TAG);

        Button addFlgihtBtn = (Button) view.findViewById(R.id.button_add);
        Button updateFlightBtn = (Button) view.findViewById(R.id.update_flight);
        Button deleteFlgihtBtn = (Button) view.findViewById(R.id.delete_flight);

        mRecView = (RecyclerView) view.findViewById(R.id.rec_flight_managing);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        managerViewFlight = new AdapterManagerViewFlight(mDataset);

        //设置自定义的RecyclerView适配器
        mRecView.setAdapter(managerViewFlight);

        addFlgihtBtn.setOnClickListener(addOnClickListener);
        updateFlightBtn.setOnClickListener(updateOnClickListener);
        deleteFlgihtBtn.setOnClickListener(deleteOnClickListener);

        return view;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
   /* public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecView.setLayoutManager(mLayoutManager);
        mRecView.scrollToPosition(scrollPosition);
    }*/

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

    private void initDataset() {
        mDataset = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            mDataset[i] = "This is element #" + i;
        }
    }


    /**
     * 自定义RecyclerView适配器
     */
    private class AdapterManagerViewFlight extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final String TAG = "CustomAdapter";

        private LayoutInflater mInflater;
        private Context mContext;
        public List<ManagerFlightDatas> mDatas = new ArrayList<>();
        private String[] mDataSet;

        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView dwhereFrom;
            TextView dwhereTo;
            TextView dtimeBegin;
            TextView dtimeEnd;
            TextView dtransCity;
            TextView dDay;

            public MyViewHolder(final View itemView) {
                super(itemView);
                dwhereFrom = (TextView) itemView.findViewById(R.id.where_from);
               /* dwhereTo = (TextView) itemView.findViewById(R.id.where_to);
                dtimeBegin = (TextView) itemView.findViewById(R.id.time_begin);
                dtimeEnd = (TextView) itemView.findViewById(R.id.time_end);
                dtransCity = (TextView) itemView.findViewById(R.id.trans_city);
                dDay = (TextView) itemView.findViewById(R.id.day);*/
                /*itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, ActivityFlightDetails.class);
                        //intent.putExtra()
                        intent.putExtra("id", (int) itemView.getTag());
                        mContext.startActivity(intent);
                    }
                });*/
            }

        }

        public AdapterManagerViewFlight(String[] dataSet) {
            mDataset = dataSet;
        }

        public AdapterManagerViewFlight(Context context, List<ManagerFlightDatas> mDatas) {
            this.mContext = context;
            this.mDatas = mDatas;
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_flight_view, parent, false);

            MyViewHolder viewHolder = new MyViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            ((MyViewHolder) holder).dwhereFrom.setText(mDataSet[position]);

/*          ((MyViewHolder) holder).dwhereFrom.setText(mDatas.get(position).getWhereFrom());
            ((MyViewHolder) holder).dwhereTo.setText(mDatas.get(position).getWhereTo());
            ((MyViewHolder) holder).dtimeBegin.setText(mDatas.get(position).getTimeBegin());
            ((MyViewHolder) holder).dtimeEnd.setText(mDatas.get(position).getTimeEnd());
            ((MyViewHolder) holder).dtransCity.setText(mDatas.get(position).getTransCity());
            ((MyViewHolder) holder).dDay.setText((mDatas.get(position).getDay()));
            ((MyViewHolder) holder).itemView.setTag(position);
*/
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
            //return mDatas.size();
        }

    }

}

