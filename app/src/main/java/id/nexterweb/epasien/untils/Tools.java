package id.nexterweb.epasien.untils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import id.nexterweb.epasien.LoginActivity;

public class Tools {
    Context ctx;
    SharedPreferences pref;
    SharedPreferences.Editor prefEditor;

    public static final String PREF_NAME = "pref_epasien";
    public static final String PREF_No_RM = "no_rm";
    public static final String PREF_NAMA = "nama_pasien";
    public static final String PREF_TGL_LAHIR = "tgl_lahir";
    public static final String PREF_JENIS_KEL = "jenis_kelamin";
    public static final String PREF_TEMPAT_LAHIR = "tempat_lahir";
    public static final String PREF_ALAMAT = "alamat_pasien";
    public static final String PREF_TELP = "no_telp";
    public static final String PREF_STATUS_LOGIN = "status_login";

    public Tools(Context ctx) {
        this.ctx = ctx;
        pref = ctx.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefEditor = pref.edit();

    }

    public void saveSPBoolean(String keySP, boolean value){
        prefEditor.putBoolean(keySP, value);
        prefEditor.commit();
    }

    public String getNo_rm() {
        return pref.getString(PREF_No_RM,"");
    }

    public String getNama_pasien() {
        return  pref.getString(PREF_NAMA,"");
    }

    public String getTmp_lahir(){
        return pref.getString(PREF_TEMPAT_LAHIR,"");
    }

    public String getTgl_lahir() {
        return pref.getString(PREF_TGL_LAHIR,"");
    }

    public String getAlamat(){
        return pref.getString(PREF_ALAMAT,"");
    }

    public String getJenis_kelamin(){
        return pref.getString(PREF_JENIS_KEL,"");
    }

    public String getTelp(){
        return pref.getString(PREF_TELP,"");
    }

    public Boolean getStatusLogin(){
        return pref.getBoolean(PREF_STATUS_LOGIN,false);
    }

    public void setLogin(boolean isLoggedIn) {

        prefEditor.putBoolean(PREF_STATUS_LOGIN, isLoggedIn);
        // commit changes
        prefEditor.commit();
    }

    public Boolean isLoggedIn(){
        return pref.getBoolean(PREF_STATUS_LOGIN, false);
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        prefEditor.clear();
        prefEditor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(ctx, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        ctx.startActivity(i);
    }
}
