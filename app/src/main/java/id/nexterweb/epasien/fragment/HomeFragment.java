package id.nexterweb.epasien.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import id.nexterweb.epasien.MainActivity;
import id.nexterweb.epasien.R;
import id.nexterweb.epasien.RegisterActivity;
import id.nexterweb.epasien.rest.ApiService;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button btnRegister;
    private Button btnAntrian;
    private  Button btnHistory;
    ProgressDialog loading;

    public HomeFragment() {
        // Required empty public constructor
    }

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ((MainActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((MainActivity)getActivity()).getSupportActionBar().setIcon(R.drawable.ic_home_white);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(" E-Pasien RSUD Kraton");

        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        btnRegister = (Button)view.findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent registerIntent = new Intent(getActivity(), RegisterActivity.class);
//                    String tglperiksa = "26-09-2018";
//                    registerIntent.putExtra("tgl_periksa", tglperiksa);
                    startActivity(registerIntent);

                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getActivity().getApplicationContext(),"Error Try Again!",Toast.LENGTH_LONG);
                }
            }
        });

        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

}
