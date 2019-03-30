package com.example.inotify.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.example.inotify.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabDashBoardFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabDashBoardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabDashBoardFragment extends Fragment {




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabDashBoardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabDashBoardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabDashBoardFragment newInstance(String param1, String param2) {
        TabDashBoardFragment fragment = new TabDashBoardFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_dash_board, container, false);

        AnyChartView anyChartView = view.findViewById(R.id.any_chart_view);
        APIlib.getInstance().setActiveAnyChartView(anyChartView);

        Pie pie = AnyChart.pie();

        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("App1", 6371664));
        data.add(new ValueDataEntry("App12", 789622));
        data.add(new ValueDataEntry("App13", 7216301));
        data.add(new ValueDataEntry("App14", 1486621));
        data.add(new ValueDataEntry("App15", 1200000));

        pie.data(data);

        pie.title("Fruits imported in 2015 (in kg)");

        anyChartView.setChart(pie);

        AnyChartView anyChartView1 = view.findViewById(R.id.any_chart_view1);
        APIlib.getInstance().setActiveAnyChartView(anyChartView1);

        Pie pie1 = AnyChart.pie();

        List<DataEntry> data1 = new ArrayList<>();
        data1.add(new ValueDataEntry("App2", 6371664));
        data1.add(new ValueDataEntry("App21", 789622));
        data1.add(new ValueDataEntry("App22", 7216301));
        data1.add(new ValueDataEntry("App23", 1486621));
        data1.add(new ValueDataEntry("App24", 1200000));

        pie1.data(data1);

        anyChartView1.setChart(pie1);

        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        pie.title("First chart");
        APIlib.getInstance().setActiveAnyChartView(anyChartView1);
        pie1.title("Second chart");

        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
