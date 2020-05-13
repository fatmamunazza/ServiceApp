package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Recharge extends AppCompatActivity implements PaymentResultListener {

   private EditText amount;
    ImageView back;
    TextView submit;

    //KEY ID=rzp_test_V62hfKmlIk6uuQ;
    //KEY SECRET=I0s19VfEtEvkKq89DyqChap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);

        Checkout.preload(getApplicationContext());

        amount=findViewById(R.id.amount);
        back=findViewById(R.id.back);

        submit=findViewById(R.id.submit);

        amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money,0,0,0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    amount.setBackground(getDrawable(R.drawable.edittext_select));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
              overridePendingTransition(R.anim.no_animation,R.anim.slide_out_bottom);

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(amount.getText().toString().equals(""))
                {
                    Toast.makeText(Recharge.this, "Please fill amount to recharge", Toast.LENGTH_LONG).show();
                    return;
                }
                startPayment();
            }
        });

    }
    public void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Service App");
            options.put("description", "Recharge your wallet");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                options.put("image", "https://techfatimawebd.000webhostapp.com/img/icon.png");
            }
            options.put("currency", "INR");

            String payment = amount.getText().toString();

            double total = Double.parseDouble(payment);
            total = total * 100;
            options.put("amount", total);

            JSONObject preFill = new JSONObject();
            preFill.put("email", "fatmamunazza@gmail.com");
            preFill.put("contact", "7255093044");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Log.e("OnPaymentError", "Error while recharging", e);
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment successfully done! " + razorpayPaymentID, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Unable to recharge....Try again later!!!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("OnPaymentError", "Error while recharging", e);
        }
    }
}
