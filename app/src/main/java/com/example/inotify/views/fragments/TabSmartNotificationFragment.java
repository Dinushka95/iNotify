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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inotify.R;
import com.example.inotify.models.NotificationModel;
import com.example.inotify.models.ProfileModel;
import com.example.inotify.viewControllers.logic.SmartNotificationLogic;
import com.example.inotify.listners.CustomRVItemTouchListener;
import com.example.inotify.interfaces.RecyclerViewItemClickListener;
import com.example.inotify.viewControllers.adapters.SmartNotificationRecyclerViewAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabSmartNotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabSmartNotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabSmartNotificationFragment extends Fragment {

    RecyclerView recyclerView;
    private SmartNotificationRecyclerViewAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TabSmartNotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabSmartNotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TabSmartNotificationFragment newInstance(String param1, String param2) {
        TabSmartNotificationFragment fragment = new TabSmartNotificationFragment();
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


        View rootView = inflater.inflate(R.layout.fragment_tab_smart_notification, container, false);

      /*  FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.insert(0, new NotificationModel("New Movie", "Movie description","hhh","gggg","ffff","ddd","ssss","aaaa"));

            }
        });*/

        SmartNotificationLogic smartNotificationLogic = new SmartNotificationLogic(getContext());
        List<NotificationModel> data = smartNotificationLogic.getNotificationList();             //fill_with_data();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        adapter = new SmartNotificationRecyclerViewAdapter(data, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Switch aSwitch = view.findViewById(R.id.switch2);


        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(this.getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view1, int position) {


                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup_smart_notification);
                //dialog.setCancelable(false);
               // dialog.setCanceledOnTouchOutside(false);
               /* Button button = dialog.findViewById(R.id.button11);
                button.setOnClickListener(view -> {
                    // save to db -- create a new profile
                    Toast.makeText(getContext(), "Clicked at " + position, Toast.LENGTH_SHORT).show();
                });*/
                TextView textView = dialog.findViewById(R.id.showdetails_snp);
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
        }));

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
