package id.nexterweb.epasien.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import id.nexterweb.epasien.LoginActivity;
import id.nexterweb.epasien.MainActivity;
import id.nexterweb.epasien.R;
import id.nexterweb.epasien.untils.SQLiteHandler;
import id.nexterweb.epasien.untils.SessionManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private TextView txt_norm;
    private TextView txt_nama;
    private TextView txt_alamat;
    private TextView txt_tmptgl;
    private TextView txt_jnskel;
    private SQLiteHandler db;
    private SessionManager session;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ((MainActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.ic_person_white);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(" Akun");

        btnLogout = view.findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    logoutUser();
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),"Error Try Again!",Toast.LENGTH_LONG);
                }
            }
        });
        txt_norm = view.findViewById(R.id.txt_norm);
        txt_nama = view.findViewById(R.id.txt_nama);
        txt_alamat = view.findViewById(R.id.txt_alamat);
        txt_tmptgl = view.findViewById(R.id.txt_tmptgl_lahir);
        txt_jnskel = view.findViewById(R.id.txt_jnskel);
        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());

        HashMap<String, String> pasien = db.getPasienDetails();
        String norm = pasien.get("no_rm");
        String nama = pasien.get("nama_pasien");
        String alamat = pasien.get("alamat");
        String tmp_lahir = pasien.get("tempat_lahir");
        String jns_kel = pasien.get("jns_kel");
        String tgl_lahir = pasien.get("tgl_lahir");
        String tgl = tgl_lahir.substring(8,10);
        String bln = tgl_lahir.substring(5,7);
        String thn = tgl_lahir.substring(0,4);

        txt_norm.setText(norm);
        txt_nama.setText(nama);
        txt_alamat.setText(alamat);
        txt_tmptgl.setText(tmp_lahir.concat(", ").concat(tgl).concat("-").concat(bln).concat("-").concat(thn));
        if (jns_kel.equals("1")){
            txt_jnskel.setText("Laki-Laki");
        }else{
            txt_jnskel.setText("Perempuan");
        }

        return view;
    }

    private void logoutUser() {
        // SqLite database handler
        db = new SQLiteHandler(getActivity().getApplicationContext());
        // session manager
        session = new SessionManager(getActivity().getApplicationContext());
        session.setLogin(false);
        db.deleteUsers();
        // Launching the login activity
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}
