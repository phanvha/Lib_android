package com.map4d.smartcodeslib;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API_Smartcode_Interface {
    @Headers({
            "Content-Type:application/json"
    })
    @GET("/{version}/api/smartcodes")
    Call<Model_Smartcode_Data> getSmartcodeData(
            @Path("version") String version,
            @Query("location") String location

    );

}