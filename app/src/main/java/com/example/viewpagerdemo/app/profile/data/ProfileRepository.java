package com.example.viewpagerdemo.app.profile.data;

import com.example.viewpagerdemo.app.R;
import com.example.viewpagerdemo.app.ViewPagerDemoApplication;
import com.example.viewpagerdemo.app.profile.models.UserResponseModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Repository for Profile in current scenario we targeting local data i.e. we are fetching data
 * from raw folder of application resource. In future we can easily replaced that with API call
 * (network operation) using network repository.
 *
 * @author pranit
 * @version 1.0
 * @since 24/3/17
 */

public class ProfileRepository {
    private static ProfileRepository sInstance;

    /**
     * Provides an Instance of ProfileRepository
     *
     * @return
     */
    public static ProfileRepository getInstance(){
        if(sInstance == null){
            sInstance = new ProfileRepository();
        }
        return sInstance;
    }

    /**
     * Get all users from raw json
     * Note: here we are using Application context for reading data from raw json
     * we should not use application context in repository. Replace this with better option.
     *
     * @return
     * @throws IOException
     */
    public UserResponseModel getUsers() throws IOException {
        InputStream is = ViewPagerDemoApplication.getInstance().getResources().openRawResource(R.raw.users);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
            is.close();
        }

        String jsonString = writer.toString();
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonString, UserResponseModel.class);
    }
}
