package id.nexterweb.epasien.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DaftarPoli {
    @SerializedName("kd_sub_unit")
    @Expose
    private String kdSubUnit;
    @SerializedName("nama_sub_unit")
    @Expose
    private String namaSubUnit;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("Rek_P")
    @Expose
    private String rekP;
    @SerializedName("kd_tarif")
    @Expose
    private String kdTarif;
    @SerializedName("nama_tarif")
    @Expose
    private String namaTarif;

    public String getKdSubUnit() {
        return kdSubUnit;
    }

    public void setKdSubUnit(String kdSubUnit) {
        this.kdSubUnit = kdSubUnit;
    }

    public String getNamaSubUnit() {
        return namaSubUnit;
    }

    public void setNamaSubUnit(String namaSubUnit) {
        this.namaSubUnit = namaSubUnit;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getRekP() {
        return rekP;
    }

    public void setRekP(String rekP) {
        this.rekP = rekP;
    }

    public String getKdTarif() {
        return kdTarif;
    }

    public void setKdTarif(String kdTarif) {
        this.kdTarif = kdTarif;
    }

    public String getNamaTarif() {
        return namaTarif;
    }

    public void setNamaTarif(String namaTarif) {
        this.namaTarif = namaTarif;
    }

}
