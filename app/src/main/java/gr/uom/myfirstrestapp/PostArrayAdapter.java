package gr.uom.myfirstrestapp;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PostArrayAdapter extends ArrayAdapter<Post> {

    private static int counter = 0;

    private List<Post> postList;
    private final LayoutInflater inflater;
    private final int layoutResource;

    private ListView postListView;


    public PostArrayAdapter(@NonNull Context context, int resource, @NonNull List<Post> objects, ListView listView) {
        super(context, resource, objects);
        postList = objects;
        layoutResource = resource;
        inflater = LayoutInflater.from(context);
        postListView = listView;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        counter++;
        Log.d("ADAPTER", "get view in adapter just called. counter = "+counter);
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = inflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
            Log.w("VIEW_HOLDER", "View Holder Created");
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Post currentPost = postList.get(position);

        viewHolder.userId.setText(currentPost.getUserId()+"");
        viewHolder.postId.setText(currentPost.getPostId()+"");
        viewHolder.postBody.setText(currentPost.getPostBody());
        viewHolder.postTitle.setText(currentPost.getPostTitle());

        return convertView;
    }

    private class ViewHolder{
        final TextView postId;
        final TextView userId;
        final TextView postTitle;
        final TextView postBody;

        ViewHolder(View view){
            postId = view.findViewById(R.id.postIdTxt);
            userId = view.findViewById(R.id.userIdTxt);
            postTitle = view.findViewById(R.id.postTitleTxt);
            postBody = view.findViewById(R.id.postBodyTxt);
        }
    }


    @Override
    public int getCount() {
        return postList.size();
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        postListView.setAdapter(this);
    }
}
