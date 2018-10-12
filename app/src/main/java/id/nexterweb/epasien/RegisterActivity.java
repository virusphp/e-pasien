package id.nexterweb.epasien;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

import id.nexterweb.epasien.fragment.HomeFragment;
import id.nexterweb.epasien.models.DaftarPoli;
import id.nexterweb.epasien.models.ResLogin;
import id.nexterweb.epasien.models.TglPeriksa;
import id.nexterweb.epasien.rest.ApiClient;
import id.nexterweb.epasien.rest.ApiService;
import id.nexterweb.epasien.untils.SQLiteHandler;
import id.nexterweb.epasien.untils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    private SQLiteHandler db;
    private SessionManager session;
    private EditText noTelp;
    private EditText tglperiksa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pendaftaran Rawat Jalan");

        noTelp = (EditText) findViewById(R.id.edt_notelp);
        tglperiksa = (EditText) findViewById(R.id.edt_tglperiksa);
        Intent intent = getIntent();
        String tglperiksa = intent.getStringExtra("tgl_periksa");

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // session manager
        session = new SessionManager(getApplicationContext());

        HashMap<String, String> pasien = db.getPasienDetails();
        String nohp = pasien.get("no_telp");
        noTelp.setText(nohp);
//        tglPeriksa.setText(tglperiksa);
        getTglPeriksa();

    }

    public void getTglPeriksa(){
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<TglPeriksa> call = api.getTglPeriksa();
        call.enqueue(new Callback<TglPeriksa>() {
            @Override
            public void onResponse(Call<TglPeriksa> call, Response<TglPeriksa> response) {
                try {
                    tglperiksa.setText(response.body().getHasil().getTglPeriksa());
                } catch (Exception e){
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<TglPeriksa> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public void getAllDaftarPoli(){
        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<List<DaftarPoli>> call = api.getAllPoli();
        call.enqueue(new Callback<List<DaftarPoli>>() {
            @Override
            public void onResponse(Call<List<DaftarPoli>> call, Response<List<DaftarPoli>> response) {
                try {
                    List<DaftarPoli> DataPoli = response.body();
//                    List<String> listSpinner = new ArrayList<String>();

                } catch (Exception e){
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<DaftarPoli>> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

}
