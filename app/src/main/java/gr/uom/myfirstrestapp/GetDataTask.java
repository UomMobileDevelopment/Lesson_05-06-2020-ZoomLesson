package gr.uom.myfirstrestapp;

import android.os.AsyncTask;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetDataTask extends AsyncTask<String, Void, String> {

    public static final String TAG = "RestApp";

    public String jsonResult;

    public String downloadRestData(String remoteUrl){
        Log.d(TAG, "Downloading data.....");
        StringBuilder sb = new StringBuilder();
        try{
            URL url = new URL(remoteUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                Log.d(TAG, "Request Accepted");

                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String line = reader.readLine();
                while (line != null){
                    sb.append(line).append("\n");

                    line = reader.readLine();
                }
                reader.close();

                return sb.toString();
            }
            else {
                Log.d(TAG, "Something went wrong. Response code was "+ responseCode);
            }
        }catch (Exception e){
            Log.e(TAG, "Error happened! ", e);
            return "";
        }
        return "";
    }

    @Override
    protected String doInBackground(String... strings) {
        String url = strings[0];
        Log.d(TAG, "Doing task in background for url "+url);

        //Manual Way
       // return downloadRestData(url);

        final StringBuilder result = new StringBuilder();

        //Library way...
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "Response = "+response);
                        result.append(response.toString());
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                    }
                });

        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        jsonResult =  result;
        Log.d(TAG, "Just got results!");
        Log.d(TAG, jsonResult);
    }

    public String getJsonResult() {
        return jsonResult;
    }

}
