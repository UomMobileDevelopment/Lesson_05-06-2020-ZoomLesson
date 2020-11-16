package gr.uom.myfirstrestapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public static final String TAG = "JsonParser";

    private static final String USER_ID_LITERAL = "userId";
    private static final String POST_ID_LITERAL = "id";
    private static final String TITLE_ID_LITERAL = "title";
    private static final String BODY_ID_LITERAL = "body";


    public List<Post> parsePostData(String postJsonData){
        List<Post> postList = new ArrayList<>();

        try{
            JSONArray jsonPostArray = new JSONArray(postJsonData);

            for(int i=0; i< jsonPostArray.length(); i++) {
                JSONObject postJsonObject = jsonPostArray.getJSONObject(i);
                int userId = postJsonObject.getInt(USER_ID_LITERAL);
                int postId = postJsonObject.getInt(POST_ID_LITERAL);
                String title = postJsonObject.getString(TITLE_ID_LITERAL);
                String body = postJsonObject.getString(BODY_ID_LITERAL);

                Post post = new Post();
                post.setPostId(postId);
                post.setUserId(userId);
                post.setPostTitle(title);
                post.setPostBody(body);

                postList.add(post);
            }

        }catch (JSONException ex){
            Log.e(TAG, "Error in json parsing", ex);
        }

        return postList;
    }

}
