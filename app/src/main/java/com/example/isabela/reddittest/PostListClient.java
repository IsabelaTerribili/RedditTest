package com.example.isabela.reddittest;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class PostListClient {

    private Context context;

    public PostListClient(Context context) {
        this.context = context;
    }

    public Observable<Post> initObservable() {
        Retrofit urlRetrofitBuilder = new UrlRetrofitBuilder(context).createUrl(); //TODO extrair para método

        RedditAndroidService redditAndroidService = urlRetrofitBuilder.create(RedditAndroidService.class);

        Observable<Post> postObservable = redditAndroidService.listPosts();

        return postObservable;
    }

    public Observable<List<PostComment>> initObservableComments(String postId) {
        Retrofit urlRetrofitBuilder = new UrlRetrofitBuilder(context).createUrl();

        RedditAndroidService redditAndroidService = urlRetrofitBuilder.create(RedditAndroidService.class);

        Observable<List<PostComment>> postCommentObservable = redditAndroidService.listComments(postId); // passar o valor do id do post

        return postCommentObservable;
    }
}
