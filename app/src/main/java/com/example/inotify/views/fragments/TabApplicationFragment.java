package com.example.inotify.views.fragments;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.inotify.R;
import com.example.inotify.interfaces.RecyclerViewItemClickListener;
import com.example.inotify.listners.CustomRVItemTouchListener;
import com.example.inotify.models.ApplicationInfoModel;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.viewControllers.adapters.INotifyActiveAppsRecyclerViewAdapter;
import com.example.inotify.viewControllers.adapters.SmartNotificationRecyclerViewAdapter;
import com.example.inotify.viewControllers.logic.AllNotificationLogic;
import com.example.inotify.viewControllers.logic.INotifyActiveAppsLogic;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabApplicationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabApplicationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabApplicationFragment extends Fragment {

    RecyclerView recyclerView;
    private INotifyActiveAppsRecyclerViewAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Switch ssww;
    private OnFragmentInteractionListener mListener;

    Switch switch2;

    public TabApplicationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabApplicationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabApplicationFragment newInstance(String param1, String param2) {
        TabApplicationFragment fragment = new TabApplicationFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_tab_application, container, false);


        //generate list
        INotifyActiveAppsLogic iNotifyActiveAppsLogic = new INotifyActiveAppsLogic(getContext());
        //instantiate custom adapter


        List<ApplicationInfoModel> data = iNotifyActiveAppsLogic.getAllActtiveAppsNotificationList();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerviewactiveapps);

        adapter = new INotifyActiveAppsRecyclerViewAdapter(data, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

 /*       recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this.getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view1, int position) {

                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_active_application);
                //dialog.setCancelable(false);
                // dialog.setCanceledOnTouchOutside(false);
               *//* Button button = dialog.findViewById(R.id.button11);
                button.setOnClickListener(view -> {
                    // save to db -- create a new profile
                    Toast.makeText(getContext(), "Clicked at " + position, Toast.LENGTH_SHORT).show();
                });*//*
                TextView textView = dialog.findViewById(R.id.showdetails_aap);
                textView.setText("Show Details "+position);
                dialog.show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getContext(), "LongClicked at " + position, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChangeSw(Boolean checked) {
                Toast.makeText(getContext(), "SSSSSSSSSSSSSSSSSSS" + checked.toString(), Toast.LENGTH_SHORT).show();
            }
        }));*/

        return rootView;
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
