package com.example.onthi_app.Service;

import com.example.onthi_app.Model.Response;
import com.example.onthi_app.Model.Xemay;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // http://192.168.100.3:3000/api/danhsach
    String DOMAIN = "http://192.168.100.3:3000/api/";

    ApiService apiservice =new Retrofit.Builder()
            .baseUrl(ApiService.DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService.class);


    // get list danh sach
    @GET("danhsach")
    Call<Response<List<Xemay>>> getdanhsac ();

    // get chi tiet
    @GET("chitiet/{id}")
    Call<Response<Xemay>> chitietxe (@Path("id") String id);


    // them xe moi
    @POST("add")
    Call<Response<Xemay>> addXe (@Body Xemay xemay);


    // xoa
    @DELETE("delete/{id}")
    Call<Response<Xemay>> deleteXe (@Path("id") String id);

    // update
    @PUT("update/{id}")
    Call<Response<Xemay>> updateXe (@Path("id") String id,
                                    @Body Xemay xemay);

    // search
    @GET("timkiem")
    Call<Response<List<Xemay>>> searchXe (@Query("key") String key);


    // sap xep
    @GET("sapxep")
    Call<Response<List<Xemay>>> sortXe (@Query("type") int type);
}
