package com.flexion.flexionfunflowers.utils;

import com.flexion.flexionfunflowers.helpers.FlexionConsts;
import com.flexion.flexionfunflowers.services.FlexionPurchaseAPIService;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.List;

/**
 * Created by Rupi on 7/17/2017.
 */

public class FlexionIntegration implements Integration {

    private FlexionPurchaseAPIService purchaseAPIService;
    private String developerId;

    public FlexionIntegration() {
        this(FlexionConsts.DEVELOPERID);
    }

    public FlexionIntegration(String developerId) {
        this.developerId = developerId;
        this.purchaseAPIService = new FlexionPurchaseAPIService();
    }

    @Override
    public Purchase buy(String itemId) {
        return purchaseAPIService.buy(itemId, developerId);
    }

    @Override
    public List<Purchase> getPurchases() {
        return purchaseAPIService.getPurchases(developerId);
    }

    @Override
    public void consume(Purchase purchase) {
        purchaseAPIService.consume(purchase, developerId);
    }
}
