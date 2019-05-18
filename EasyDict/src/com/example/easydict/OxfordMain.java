package com.example.easydict;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class OxfordMain extends AsyncTask<String, Integer, String> {
	Context context;
	 static  String word ;
	 final String app_id = "0feeb6c0";
     final String app_key = "7a041f3ce596b100aa5214ab3816a200";
     
	public OxfordMain(Context context ){
		this.context = context;
		
    	
    	
	}
    @Override
    protected String doInBackground(String... params){
        try {
            URL url = new URL(params[0]);
         
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }
        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
			JSONObject  jsonObject = new JSONObject(result);
			JSONArray  jsonresults = jsonObject.getJSONArray("results");
			
		JSONObject lentries = jsonresults.getJSONObject(0);
		JSONArray isLentries = lentries.getJSONArray("lexicalEntries");
		
		JSONObject  entries = isLentries.getJSONObject(0);
		JSONArray isentries = entries.getJSONArray("entries");
		
		JSONObject senses = isentries.getJSONObject(0);
		JSONArray issenses = senses.getJSONArray("senses");
		
		JSONObject def = issenses.getJSONObject(0);
		JSONArray isdef = def.getJSONArray("definitions");
		
		word = isdef.getString(0);
		
	// Toast.makeText(context, HomePage.text+ ":-"+"\n"+ word , Toast.LENGTH_LONG).show();
	
		
		
		
		
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
    }



}