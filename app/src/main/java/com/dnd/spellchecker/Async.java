package com.dnd.spellchecker;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Async extends AsyncTask<Void, Void, String> {
    public String jsonToString;
    JSONObject jsonRead = new JSONObject();


    @Override
    public String doInBackground(Void... params) {

        try {

            URL url = new URL("https://www.dnd5eapi.co/api/spells");
            HttpURLConnection  urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection = (HttpURLConnection) url.openConnection();


            int code = urlConnection.getResponseCode();
            if (code != 200) {
                throw new IOException("Invalid response from server: " + code);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    urlConnection.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                jsonRead.put("Spell", line);

            }
            jsonToString = jsonRead.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonToString;
    }

}