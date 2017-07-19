package com.flexion.flexionfunflowers;

import com.flexion.flexionfunflowers.helpers.FlexionConsts;
import com.flexion.flexionfunflowers.models.FlexionPurchase;
import com.flexion.flexionfunflowers.utils.FlexionIntegration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.flexionmobile.codingchallenge.integration.Purchase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest extends IntegrationTestRunner {

    private FlexionIntegration flexionIntegration;

    @Before
    public void initialize() {
        flexionIntegration = new FlexionIntegration(FlexionConsts.DEVELOPERID);
    }

    @Test
    public void test_buy() throws Exception {
        Purchase purchase = flexionIntegration.buy("item1");
        assertEquals("item1", purchase.getItemId());
    }

    @Test
    public void test_getPurchase() throws Exception {
        int beforeCount = flexionIntegration.getPurchases().size();
        flexionIntegration.buy("item2");
        assertEquals(beforeCount++, flexionIntegration.getPurchases().size());
    }

    @Test
    public void test_consume() throws Exception {
        int beforeCount = flexionIntegration.getPurchases().size();
        Purchase purchase = flexionIntegration.buy("item3");
        flexionIntegration.consume(purchase);
        assertEquals(beforeCount, flexionIntegration.getPurchases().size());
    }
}