package gr.uom.myfirstrestapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class GetDataTask extends AsyncTask<String, Void, List<Post>> {

    public static final String TAG = "RestApp";

    public List<Post> postList;

    private PostArrayAdapter adapter;

    public GetDataTask(PostArrayAdapter adapter){
        this.adapter = adapter;
    }

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
            Log.e(TAG, "Error happened!", e);
            return "";
        }
        return "";
    }

    @Override
    protected List<Post> doInBackground(String... strings) {
        String url = strings[0];
        Log.d(TAG, "Doing task in background for url "+url);

        String postJson = downloadRestData(url);

        JsonParser jsonParser = new JsonParser();
        return jsonParser.parsePostData(postJson);
    }

    @Override
    protected void onPostExecute(List<Post> posts) {
        postList =  posts;
        Log.d(TAG, "Just got results!");

        for(Post post : postList) {
            Log.d(TAG, post.toString());
        }

        adapter.setPostList(postList);

    }

    public List<Post> getPostList() {
        return postList;
    }
}
