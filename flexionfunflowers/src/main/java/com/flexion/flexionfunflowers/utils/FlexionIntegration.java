package com.flexion.flexionfunflowers.utils;

import android.util.Log;

import com.flexion.flexionfunflowers.helpers.FlexionConsts;
import com.flexion.flexionfunflowers.models.FlexionPurchase;
import com.flexion.flexionfunflowers.models.FlexionPurchases;
import com.flexion.flexionfunflowers.services.FlexionPurchaseAPIService;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rupi on 7/17/2017.
 */

public class FlexionIntegration implements Integration {

    private static Retrofit retrofit = null;
    FlexionPurchaseAPIService purchaseAPIService = null;

    private String developerId;
    private String tag;

    public FlexionIntegration(String tag) {
        this(FlexionConsts.DEVELOPERID, tag);
    }

    public FlexionIntegration(String developerId, String tag) {
        this.developerId = developerId;
        this.tag = tag;
        init();
    }

    private void init() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(FlexionConsts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        purchaseAPIService = retrofit.create(FlexionPurchaseAPIService.class);
    }

    @Override
    public Purchase buy(String itemId) {
        FlexionPurchase purchase = null;
        Call<FlexionPurchase> call = purchaseAPIService.buy(FlexionConsts.DEVELOPERID, itemId);
        try {
            Response<FlexionPurchase> result = call.execute();
            if (result.isSuccessful()) {
                purchase = result.body();
            }
        } catch (IOException e) {
            Log.e(tag, e.toString());
        }

        return purchase;
    }

    @Override
    public List<Purchase> getPurchases() {
        List purchases = new ArrayList();
        Call<FlexionPurchases> call = purchaseAPIService.getPurchases(FlexionConsts.DEVELOPERID);
        try {
            Response<FlexionPurchases> result = call.execute();
            if (result.isSuccessful()) {
                purchases = result.body().getPurchases();
            }
        } catch (IOException e) {
            Log.e(tag, e.toString());
        }

        return purchases;
    }

    @Override
    public void consume(Purchase purchase) {
        purchaseAPIService.consume(FlexionConsts.DEVELOPERID, purchase.getId());
    }

}
