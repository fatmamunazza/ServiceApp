package com.example.serviceapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.serviceapp.HistoryActivity;
import com.example.serviceapp.R;
import com.example.serviceapp.Recharge;

public class Wallet extends Fragment {

    public Wallet() {
        // Required empty public constructor
    }

    TextView recharge;
    RelativeLayout walletHistory;

    //KEY ID=rzp_test_V62hfKmlIk6uuQ;
    //KEY SECRET=I0s19VfEtEvkKq89DyqChap4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_wallet, container, false);

        recharge=view.findViewById(R.id.recharge);
        walletHistory=view.findViewById(R.id.wallet_history);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Recharge.class));
                getActivity().overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_animation);
            }
        });

        walletHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HistoryActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_right_in,R.anim.no_animation);
            }
        });

        return view;
    }
}
