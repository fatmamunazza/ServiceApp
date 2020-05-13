package com.example.serviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.serviceapp.fragment.Profile;
import com.example.serviceapp.fragment.Wallet;

public class MainActivity extends AppCompatActivity {

    TextView profile,wallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile=findViewById(R.id.profile);
        wallet=findViewById(R.id.wallet);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile profileFragment = new Profile();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).addToBackStack(null).commit();
            }
        });
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wallet walletFragment = new Wallet();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, walletFragment).addToBackStack(null).commit();
            }
        });



    }
}
