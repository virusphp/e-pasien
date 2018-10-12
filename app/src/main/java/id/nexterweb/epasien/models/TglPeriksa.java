package id.nexterweb.epasien.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import id.nexterweb.epasien.models.TglPeriksaHasil;

public class TglPeriksa {

    @SerializedName("ok")
    @Expose
    private Boolean ok;
    @SerializedName("hasil")
    @Expose
    private TglPeriksaHasil hasil;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public TglPeriksaHasil getHasil() {
        return hasil;
    }

    public void setHasil(TglPeriksaHasil hasil) {
        this.hasil = hasil;
    }
}
