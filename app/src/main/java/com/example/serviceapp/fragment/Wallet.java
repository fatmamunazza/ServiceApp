package com.example.serviceapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.serviceapp.HistoryActivity;
import com.example.serviceapp.R;
import com.example.serviceapp.Recharge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Wallet extends Fragment {

    public Wallet() {
        // Required empty public constructor
    }

    TextView recharge,balance;
    RelativeLayout walletHistory;

    //KEY ID=rzp_test_V62hfKmlIk6uuQ;
    //KEY SECRET=I0s19VfEtEvkKq89DyqChap4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wallet, container, false);

        recharge=view.findViewById(R.id.recharge);
        balance=view.findViewById(R.id.balance);
      //  walletHistory=view.findViewById(R.id.wallet_history);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Recharge.class));
                getActivity().overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_animation);
            }
        });



       /* walletHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_right_in,R.anim.no_animation);
            }
        });*/


        String url="http://serveondoor.com/servernew/Restapi/walletdetails";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.POST, url,null
                , new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject jsonObject=response.getJSONObject(0);
                    String balanceValue=jsonObject.getString("ny_money");
                     balance.setText(balanceValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("Response", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), String.valueOf(error), Toast.LENGTH_SHORT).show();

                VolleyLog.d("Error", "Error: " + error.getMessage());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("token","oUtsKvCtGd");
                headers.put("Authorization", "token");
                return headers;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(req);

        return view;
    }
}
