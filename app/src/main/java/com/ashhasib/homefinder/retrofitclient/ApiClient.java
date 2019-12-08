package com.ashhasib.homefinder.retrofitclient;

import com.ashhasib.homefinder.model.Token;
import com.ashhasib.homefinder.model.User;
import com.ashhasib.homefinder.model.UserProfile;
import com.ashhasib.homefinder.preference.UserSessionManager;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiClient {


    @GET("hello/")
    Call<String>  getHelloMessage();


    @GET("authhello/")
    Call<String> getAuthHello(@Header("Authorization")String header);



    @POST("gettoken/")
    Call<Token> getToken(@Body User user);


    @POST("register/")
    Call<UserProfile> createUser(@Body UserProfile userProfile);

}
