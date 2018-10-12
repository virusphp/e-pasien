package id.nexterweb.epasien.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TglPeriksaHasil {
    @SerializedName("tgl_periksa")
    @Expose
    private String tglPeriksa;

    public String getTglPeriksa() {
        return tglPeriksa;
    }

    public void setTglPeriksa(String tglPeriksa) {
        this.tglPeriksa = tglPeriksa;
    }
}
