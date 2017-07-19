package com.flexion.flexionfunflowers.services;

import android.util.Log;

import com.flexion.flexionfunflowers.helpers.FlexionConsts;
import com.flexion.flexionfunflowers.models.FlexionPurchase;
import com.flexion.flexionfunflowers.models.FlexionPurchases;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Rupi on 7/17/2017.
 */

public interface FlexionPurchaseAPIService {

    @GET("developer/{developerId}/buy/{itemId}")
    Call<FlexionPurchase> buy(@Path("developerId") String developerId, @Path("itemId") String itemId);

    @POST("developer/{developerId}/consume/{purchaseId}")
    Call<Void> consume(@Path("developerId") String developerId, @Path("purchaseId") String purchaseId);

    @GET("developer/{developerId}/all")
    Call<FlexionPurchases> getPurchases(@Path("developerId") String developerId);

}
