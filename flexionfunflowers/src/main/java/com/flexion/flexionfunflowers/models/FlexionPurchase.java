package com.flexion.flexionfunflowers.models;

import com.flexionmobile.codingchallenge.integration.Purchase;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rupi on 7/17/2017.
 */

public class FlexionPurchase implements Purchase {
    @SerializedName("consumed")
    private boolean consumed;

    @SerializedName("id")
    private String id;

    @SerializedName("itemId")
    private String itemId;

    public FlexionPurchase() {

    }

    public FlexionPurchase(boolean consumed, String id, String itemId) {
        this.consumed = consumed;
        this.id = id;
        this.itemId = itemId;
    }

    @Override
    public boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(boolean consumed) {
        this.consumed = consumed;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

}
