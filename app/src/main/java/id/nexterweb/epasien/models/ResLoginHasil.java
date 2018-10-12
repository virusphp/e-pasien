package id.nexterweb.epasien.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResLoginHasil {

    @SerializedName("no_RM")
    @Expose
    private Integer noRM;
    @SerializedName("nama_pasien")
    @Expose
    private String namaPasien;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("jns_kel")
    @Expose
    private Integer jnsKel;
    @SerializedName("jenis_pasien")
    @Expose
    private Integer jenisPasien;
    @SerializedName("tempat_lahir")
    @Expose
    private String tempatLahir;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kd_kelurahan")
    @Expose
    private Integer kdKelurahan;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;

    public Integer getNoRM() {
        return noRM;
    }

    public void setNoRM(Integer noRM) {
        this.noRM = noRM;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public Integer getJnsKel() {
        return jnsKel;
    }

    public void setJnsKel(Integer jnsKel) {
        this.jnsKel = jnsKel;
    }

    public Integer getJenisPasien() {
        return jenisPasien;
    }

    public void setJenisPasien(Integer jenisPasien) {
        this.jenisPasien = jenisPasien;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getKdKelurahan() {
        return kdKelurahan;
    }

    public void setKdKelurahan(Integer kdKelurahan) {
        this.kdKelurahan = kdKelurahan;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }


}
