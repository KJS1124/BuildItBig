package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jokeandroid.JokeActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.udacity.gradle.builditbigger.EndPointAsyncTask;


public class MainFragment extends Fragment {

    Button mButton;
    InterstitialAd mInterstitialAd;
    String jokeString;
    ProgressBar mPB;
    TextView jokeTextView;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_main, container, false);
        mButton =  root.findViewById(R.id.joke_button);
        AdView mAdView =  root.findViewById(R.id.adView);
        mPB =  root.findViewById(R.id.progress_bar);
        jokeTextView =  root.findViewById(R.id.instructions_text_view);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        setupInterstitialAds();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPB.setVisibility(View.VISIBLE);
                new EndPointAsyncTask(new EndPointAsyncTask.OnRequestFinish() {
                    @Override
                    public void onFinish(String s) {
                        mPB.setVisibility(View.GONE);
                        jokeString = s;
                        Intent intent=new Intent(getActivity(),JokeActivity.class);
                        intent.putExtra(JokeActivity.JOKE_KEY, jokeString);
                        startActivity(intent);
                    }
                }).execute();
            }
        });


        return root;
    }



    private void setupInterstitialAds() {
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(getActivity().getString(R.string.banner_ad_unit_id));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(getContext(), JokeActivity.class);
                intent.putExtra(JokeActivity.JOKE_KEY, jokeString);
                startActivity(intent);
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
