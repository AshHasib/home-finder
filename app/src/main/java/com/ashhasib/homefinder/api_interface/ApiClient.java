package com.ashhasib.homefinder.api_interface;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {
    @GET("hello/")
    Call<String>  getHelloMessage();
}
