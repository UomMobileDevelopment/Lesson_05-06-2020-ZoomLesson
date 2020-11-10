package gr.uom.myfirstrestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RestApp";

    private static final String REMOTE_API = "https://jsonplaceholder.typicode.com/posts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize Library of Indian guy
        AndroidNetworking.initialize(getApplicationContext());

        // Then set the JacksonParserFactory like below
        AndroidNetworking.setParserFactory(new JacksonParserFactory());

        setContentView(R.layout.activity_main);

        GetDataTask getDataTaskObject = new GetDataTask();
        getDataTaskObject.execute(REMOTE_API);

        Log.d(TAG, "STARTED Async Request Execution for Web Service Data");
    }

}