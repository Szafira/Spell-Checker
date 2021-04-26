package com.dnd.spellchecker;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Async extends AsyncTask<Void, Void, String> {
    public String jsonToString;
    JSONObject jsonRead = new JSONObject();
    JSONArray results = new JSONArray();
    String spellNames = new String();
    MainActivity mainActivity = new MainActivity();
    String CategoryUrl= mainActivity.CategoryUrl;
    String finalResults = "";

    @Override
    public String doInBackground(Void... params) {

        try {

            String BaseUrl = "https://www.dnd5eapi.co/api/classes/";
            String fullUrl = BaseUrl + CategoryUrl + "/spells";

            System.out.println(fullUrl);
            URL url = new URL("https://www.dnd5eapi.co/api/classes/paladin/spells");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
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
                jsonToString = line;
            }
            JSONObject jsonParsed = (JSONObject) new JSONTokener(jsonToString).nextValue();
            jsonToString = jsonParsed.toString();
            JSONArray results = jsonParsed.getJSONArray("results");
            jsonToString = results.toString();
            Integer spellCount = jsonParsed.getInt("count");


            for (int i = 0; i < spellCount; i++)
            { jsonRead = results.getJSONObject(i);
                System.out.println("For test" + jsonRead);
                jsonRead = results.getJSONObject(i);

                finalResults = finalResults +"\n"+ i +"\n"+jsonRead.getString("name");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalResults;
    }

}