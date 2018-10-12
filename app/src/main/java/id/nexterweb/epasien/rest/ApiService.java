package id.nexterweb.epasien.rest;

import java.util.List;

import id.nexterweb.epasien.models.DaftarPoli;
import id.nexterweb.epasien.models.ResLogin;
import id.nexterweb.epasien.models.TglPeriksa;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiService {

    @FormUrlEncoded
    @POST("sendpasien")
    Call<ResLogin> loginpasien(@Field("no_RM") String username,
                               @Field("tgl_lahir") String tgl_lahir);

    @GET("tanggalperiksa")
    Call<TglPeriksa> getTglPeriksa();

    @GET("getalltarifkarcis")
    Call<List<DaftarPoli>> getAllPoli();
}
