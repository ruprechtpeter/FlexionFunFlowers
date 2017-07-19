package com.flexion.flexionfunflowers.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.flexion.flexionfunflowers.R;
import com.flexion.flexionfunflowers.helpers.FlexionConsts;
import com.flexion.flexionfunflowers.models.FlexionPurchase;
import com.flexion.flexionfunflowers.models.FlexionPurchases;
import com.flexion.flexionfunflowers.services.FlexionPurchaseAPIService;
import com.flexion.flexionfunflowers.utils.FlexionIntegration;
import com.flexionmobile.codingchallenge.integration.Purchase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.btn_buy)
    Button btn_buy;
    @BindView(R.id.btn_consume)
    Button btn_consume;
    @BindView(R.id.btn_purchases)
    Button btn_purchases;

    private FlexionIntegration flexionIntegration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        flexionIntegration = new FlexionIntegration(TAG);

        setOnClickListeners();
    }


    private void setOnClickListeners() {
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BuyTask().execute("item1");
            }
        });

        btn_consume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlexionPurchase purchase = null;
                new ConsumeTask().execute(purchase);
            }
        });

        btn_purchases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetPurchasesTask().execute();
            }
        });
    }

    class BuyTask extends AsyncTask<String, Void, FlexionPurchase> {

        @Override
        protected FlexionPurchase doInBackground(String... strings) {
            Purchase purchase;
            purchase = flexionIntegration.buy(strings[0]);
            return (FlexionPurchase) purchase;
        }

        @Override
        protected void onPostExecute(FlexionPurchase flexionPurchase) {
            if (flexionPurchase != null) {
                Log.d(TAG, flexionPurchase.getItemId());
            } else {
                Log.e(TAG, "The call buy no response!");
            }
        }
    }

    class GetPurchasesTask extends AsyncTask<Void, Void, FlexionPurchases> {

        @Override
        protected FlexionPurchases doInBackground(Void... voids) {
            List result = new ArrayList();
            result = flexionIntegration.getPurchases();
            FlexionPurchases purchases = new FlexionPurchases();
            purchases.setPurchases(result);
            return purchases;
        }

        @Override
        protected void onPostExecute(FlexionPurchases flexionPurchases) {
            if (flexionPurchases != null) {
                Log.d(TAG, String.valueOf(flexionPurchases.getPurchases().size()));
            } else {
                Log.e(TAG, "The call get purchases no response!");
            }

        }
    }

    class ConsumeTask extends AsyncTask<Purchase, Void, Void> {

        @Override
        protected Void doInBackground(Purchase... purchases) {
            flexionIntegration.consume(purchases[0]);
            return null;
        }
    }

}
