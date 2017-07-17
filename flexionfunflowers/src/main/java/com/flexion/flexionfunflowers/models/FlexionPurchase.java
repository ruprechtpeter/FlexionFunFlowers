package com.flexion.flexionfunflowers.models;

import com.flexionmobile.codingchallenge.integration.Purchase;

/**
 * Created by Rupi on 7/17/2017.
 */

public class FlexionPurchase implements Purchase {
    private boolean consumed;
    private String id;
    private String itemId;

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
