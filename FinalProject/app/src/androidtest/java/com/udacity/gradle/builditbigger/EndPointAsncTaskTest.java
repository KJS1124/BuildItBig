package com.udacity.gradle.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Amanjeet Singh on 20/12/17.
 */
@RunWith(AndroidJUnit4.class)
public class EndPointAsncTaskTest {

    private String joke;

    @Test
    public void testForJokes() throws Exception {
        //Make sure to deploy server locally before running tests
        EndPointAsyncTask endPointAsyncTask = new EndPointAsyncTask(new EndPointAsyncTask.OnRequestFinish() {
            @Override
            public void onFinish(String s) {

            }
        });
        endPointAsyncTask.execute();
        try {
            Thread.sleep(10000);
            joke = endPointAsyncTask.get();
            assertNotNull("Joke fetched successfully", joke);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}