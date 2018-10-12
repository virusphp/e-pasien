package id.nexterweb.epasien.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.nexterweb.epasien.MainActivity;
import id.nexterweb.epasien.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {


    public NotificationFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.ic_notifications_white);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(" Notifikasi");

        return view;
    }

}
