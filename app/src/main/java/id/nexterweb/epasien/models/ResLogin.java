package id.nexterweb.epasien.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResLogin {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("pesan")
    @Expose
    private String pesan;

    @SerializedName("hasil")
    @Expose
    private ResLoginHasil hasil;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ResLoginHasil getHasil() {
        return hasil;
    }

    public void setHasil(ResLoginHasil hasil) {
        this.hasil = hasil;
    }

}
