package sk.upjs.nelinocka.elektronicke_volby;

import java.util.List;

import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public interface NU_VoteApi {
    String BASE_URL =
            "https://s.ics.upjs.sk/~pstanova/android/vote/index.php/";

    @GET("Muggles")
    Call<List<NU_User>> getUsers();

    @GET("Muggles/{OP}")
    Call<List<NU_User>> getUsersOP();

    @GET("Muggles/{PIN}")
    Call<List<NU_User>> getUsersPIN();

    Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    NU_VoteApi API = RETROFIT.create(NU_VoteApi.class);
}