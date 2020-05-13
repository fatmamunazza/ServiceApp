package com.example.serviceapp.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.serviceapp.EditProfile;
import com.example.serviceapp.R;
import com.example.serviceapp.Recharge;

public class Profile extends Fragment {

    public Profile() {
        // Required empty public constructor
    }

    TextView edit;
    LinearLayout share,wallet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile, container, false);

        edit=view.findViewById(R.id.edit);
        share=view.findViewById(R.id.share);
        wallet=view.findViewById(R.id.wallet);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfile.class));
                getActivity().overridePendingTransition(R.anim.slide_in_bottom,R.anim.no_animation);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poem_string = getString(R.string.share_content);

                Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.share);
                String  path = MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), b, "Title", null);


                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/*");
                    Uri file = Uri.parse(path);
                    share.putExtra(Intent.EXTRA_STREAM,file);
                    share.putExtra(Intent.EXTRA_TEXT, poem_string);
                    startActivity(Intent.createChooser(share, "Share Via"));
                }
                else{
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            200);
                }
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Wallet walletFragment = new Wallet();
                getFragmentManager().beginTransaction().replace(R.id.container, walletFragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
