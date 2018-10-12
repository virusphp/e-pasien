package id.nexterweb.epasien;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import id.nexterweb.epasien.models.ResLogin;
import id.nexterweb.epasien.rest.ApiClient;
import id.nexterweb.epasien.rest.ApiService;
import id.nexterweb.epasien.untils.SQLiteHandler;
import id.nexterweb.epasien.untils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private EditText inputUserid;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
    private SQLiteHandler db;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUserid = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = inputUserid.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if(username.isEmpty()){
                    inputUserid.setError("Harap Masukan User ID");
                    inputUserid.requestFocus();
                    return;
                }else if(password.isEmpty()){
                    inputPassword.setError("Harap Masukan password");
                    inputPassword.requestFocus();
                    return;
                }else{
                    try{
                        //proses login
                        loginProses(username, password);
                    }catch (Exception e){
                        dialog.dismiss();
                        showDialog("Error connection", "Periksa Kembali Koneksi Internet Anda " );
                    }

                }
            }
        });

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());
        // Session manager
        session = new SessionManager(getApplicationContext());

        // jika status session login langsung ke mainactivity
        if (session.isLoggedIn()){
            startActivity(new Intent(LoginActivity.this, MainActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void loginProses(final String username,final String password){

        dialog = ProgressDialog.show(LoginActivity.this, "Login", "Please Wait");

        String tgl = password.substring(0,2);
        String bln = password.substring(2,4);
        String thn = password.substring(4);
        final String tgl_lahir = thn.concat("-").concat(bln).concat("-").concat(tgl);

        ApiService api = ApiClient.getClient().create(ApiService.class);
        Call<ResLogin> call = api.loginpasien(username,tgl_lahir);
        call.enqueue(new Callback<ResLogin>() {
            @Override
            public void onResponse(Call<ResLogin> call, Response<ResLogin> response) {
//                Log.d(TAG, "Login Response: " + response.toString());
//                hideDialog();
//                JSONObject jObj = new JSONObject(response.body().getOk());
                Boolean status = response.body().getOk();
                String pesan = response.body().getPesan();

                if (status.equals(true)) {

                    //ubah status login
                    session.setLogin(true);

//                    JSONObject user = jObj.getJSONObject("user");
                    Integer no_rm = response.body().getHasil().getNoRM();
                    String nama_pasien = response.body().getHasil().getNamaPasien();
                    Integer jns_kel = response.body().getHasil().getJnsKel();
                    Integer jenis_pasien = response.body().getHasil().getJenisPasien();
                    String tempat_lahir = response.body().getHasil().getTempatLahir();
                    String tgl_lahir = response.body().getHasil().getTglLahir();
                    String alamat = response.body().getHasil().getAlamat();
                    String no_telp = response.body().getHasil().getNoTelp();
//                    Session(true, no_rm,nama_pasien,jenis_kel,tmp_lahir,tgl_lahir,alamat,no_telp);
                    db.addPasien(no_rm,nama_pasien,tempat_lahir,tgl_lahir,jns_kel,jenis_pasien,alamat,no_telp);
                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showDialog("Gagal Login", pesan );
//                    Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();
                }

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResLogin> call, Throwable t) {
                t.printStackTrace();
//                Toast.makeText(getApplicationContext(), "Cek Koneksi Internet Anda", Toast.LENGTH_LONG).show();
                showDialog("Error connection", "Periksa Kembali Koneksi Internet Anda " );
                dialog.dismiss();
            }
        });

    }

    public void showDialog(String judul, String pesan) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(judul);
        builder.setMessage(pesan);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
