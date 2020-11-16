package gr.uom.myfirstrestapp;

public class Post {

    private int userId;
    private int postId;
    private String postTitle;
    private String postBody;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", postId=" + postId +
                ", postTitle='" + postTitle + '\'' +
                ", postBody='" + postBody + '\'' +
                '}';
    }
}
