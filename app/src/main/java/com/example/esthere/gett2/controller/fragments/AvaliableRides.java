package com.example.esthere.gett2.controller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.esthere.gett2.R;
import com.example.esthere.gett2.model.backend.IAction;
import com.example.esthere.gett2.model.datasource.Globals;
import com.example.esthere.gett2.model.entities.Driver;
import com.example.esthere.gett2.model.entities.Ride;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link AvaliableRides.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link AvaliableRides#newInstance} factory method to
// * create an instance of this fragment.
// */
public class AvaliableRides extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    protected ArrayList<Ride> rides;
//
//    private OnFragmentInteractionListener mListener;
//
//    public AvaliableRides() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment AvaliableRides.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static AvaliableRides newInstance(String param1, String param2) {
//        AvaliableRides fragment = new AvaliableRides();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
////    @Override
////    public void onCreate(Bundle savedInstanceState) {
//////        super.onCreate(savedInstanceState);
//////        if (getArguments() != null) {
//////            mParam1 = getArguments().getString(ARG_PARAM1);
//////            mParam2 = getArguments().getString(ARG_PARAM2);
//////        }
////    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_avaliable_rides, container, false);
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    protected ArrayList<Ride> rides;

    public AvaliableRides() {
        Globals.driver=new Driver("Lachman","Itamar","123456789","0543456354","Itamar@gmail.com","9876543234567876","1234");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_avaliable_rides, container, false);
        final ListView listAvaliableRides = (ListView) view.findViewById(R.id.listOpenTravels);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Globals.backend.openRides(new IAction<ArrayList<Ride>>() {
                    @Override
                    public void onSuccess(ArrayList<Ride> obj) {
                        rides = obj;
                        ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>( getActivity(), R.layout.ride, rides) {
                            @NonNull
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                if (convertView == null)
                                    convertView = View.inflate(getActivity(),R.layout.ride,null);

                                EditText name = (EditText) convertView.findViewById(R.id.name);
                                EditText tel = (EditText) convertView.findViewById(R.id.tel);
                                EditText source = (EditText) convertView.findViewById(R.id.source);
                                EditText target = (EditText) convertView.findViewById(R.id.target);

                                Button claim = (Button) convertView.findViewById(R.id.claim);
                                Button unclaim = (Button) convertView.findViewById(R.id.unclaim);

                                claim.setTag(rides.get(position).getEmail());
                                claim.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String key = (String)((Button) view).getTag();
                                        Globals.backend.Claim(key, Globals.driver.getEmail(), new IAction<Boolean>() {
                                            @Override
                                            public void onSuccess(Boolean obj) {
                                                // Dialogs.Dialog(getActivity(),getString(R.string.FIREBASE),getString(R.string.CLAIMED),getString(R.string.BUTTON_CLOSE));
                                                return;
                                            }

                                            @Override
                                            public void onFailure(Exception ex) {
                                                // Dialogs.Dialog(getActivity(),getString(R.string.FIREBASE),ex.getMessage(),getString(R.string.BUTTON_CLOSE));
                                                return;
                                            }
                                        });

                                    }
                                });

                                unclaim.setVisibility(View.GONE);

                                name.setText(rides.get(position).getCustomer().getName());
                                tel.setText(rides.get(position).getCustomer().getTel());
                                source.setText(rides.get(position).getSourceLocation().toString());
                                target.setText(rides.get(position).getTargetLocation().toString());



                                return convertView;
                            }
                        };

                        listAvaliableRides.setAdapter(adapter);

                    }
                    @Override
                    public void onFailure(Exception exception) {

                    }
                });
                return null;
            }
        }.execute();
        return  view;
    }
}
