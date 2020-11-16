package gr.uom.myfirstrestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.androidnetworking.AndroidNetworking;
import com.jacksonandroidnetworking.JacksonParserFactory;

import java.util.ArrayList;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "RestApp";

    private static final String REMOTE_API = "https://jsonplaceholder.typicode.com/posts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView postListView = findViewById(R.id.postListView);

        PostArrayAdapter postArrayAdapter =
                new PostArrayAdapter(this,
                        R.layout.list_record,
                        new ArrayList<Post>(),
                        postListView
                );

        GetDataTask getDataTaskObject = new GetDataTask(postArrayAdapter);
        getDataTaskObject.execute(REMOTE_API);

        Log.d(TAG, "STARTED Async Request Execution for Web Service Data");
    }

}