package com.example.isabela.reddittest.presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.isabela.reddittest.ListPostsAdapter;
import com.example.isabela.reddittest.Post;
import com.example.isabela.reddittest.PostListClient;
import com.example.isabela.reddittest.PostModel;
import com.example.isabela.reddittest.R;
import com.example.isabela.reddittest.RedditAndroidService;
import com.example.isabela.reddittest.UrlRetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;


public class ListPostsActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    List<PostModel> postModels;
    ListPostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);
        ButterKnife.bind(this);

        setUpToolbar();

        PostListClient postListClient = new PostListClient();
        Observable<Post> postObservable = postListClient.initObservable();

        postObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Post>() {
                    @Override
                    public void accept(Post post) throws Exception {
                        post.getKind();
                        post.getDataList();
                    }
                });


        postModels = new ArrayList<>();

        postModels.add(new PostModel("What is something you hope to see in your lifetime?", "blablabla", 5402));
        postModels.add(new PostModel("What is something you hope to see in your lifetime?", "blablabla", 5402));
        postModels.add(new PostModel("What is something you hope to see in your lifetime?", "blablabla", 5402));


        adapter = new ListPostsAdapter(postModels, ListPostsActivity.this);

        recyclerView.setAdapter(adapter);

        LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);

    }

    public void setUpToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.ic_reddit_white);
        toolbar.setTitle(getString(R.string.list_title));
        setSupportActionBar(toolbar);
    }
}


