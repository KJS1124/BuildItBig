package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class EndPointAsyncTask extends AsyncTask<Void, Void, String> {

    private OnRequestFinish onRequestFinish;

    public EndPointAsyncTask(OnRequestFinish onRequestFinish) {
        this.onRequestFinish = onRequestFinish;
    }

    @Override
    protected String doInBackground(Void... voids) {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                .setApplicationName("Build it Bigger")
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });
        MyApi myApiService = builder.build();
        String joke = null;

        try {
            joke = myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            onRequestFinish.onFinish(s);
        }
    }

    public interface OnRequestFinish {
        void onFinish(String s);
    }
}