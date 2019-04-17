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


public class MainFragment extends Fragment {

    Button mButton;
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
        mPB =  root.findViewById(R.id.progress_bar);
        jokeTextView =  root.findViewById(R.id.instructions_text_view);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPB.setVisibility(View.VISIBLE);
                new EndPointAsyncTask(new EndPointAsyncTask.OnRequestFinish() {
                    @Override
                    public void onFinish(String s) {
                        mPB.setVisibility(View.GONE);
                        jokeString = s;
                        Intent intent = new Intent(getActivity(), JokeActivity.class);
                        intent.putExtra(JokeActivity.JOKE_KEY, jokeString);
                        startActivity(intent);
                    }
                }).execute();
            }
        });


        return root;
    }


}
