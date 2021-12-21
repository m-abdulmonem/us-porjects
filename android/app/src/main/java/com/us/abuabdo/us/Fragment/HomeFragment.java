package com.us.abuabdo.us.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.us.abuabdo.us.Adapter.PostsAdapter;
import com.us.abuabdo.us.Adapter.StoriesAdapter;
import com.us.abuabdo.us.Model.Posts;
import com.us.abuabdo.us.Model.Story;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView,stories;
    private List<Posts> mPost;
    private TextView news,popular;
    private ImageView search,notification;
    private PostsAdapter postsAdapter;
    private boolean popular_clicked = false;
    String image = "https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, searchFragment);
                fragmentTransaction.commit();
//                startActivity(new Intent(getContext(), Search.class));
            }
        });


        notification = view.findViewById(R.id.notification);
        news = view.findViewById(R.id.news);
        popular = view.findViewById(R.id.popular);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notificationfragment notificationfragment = new Notificationfragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, notificationfragment);
                fragmentTransaction.commit();
            }
        });
        recyclerView = view.findViewById(R.id.recycler_view_posts);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mPost = new ArrayList<>();



        stories = view.findViewById(R.id.story_RecyclerView);
        stories.setHasFixedSize(true);
        stories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.HORIZONTAL,false));

        List<Story> story = new ArrayList<>();
       // int id, int userID, String userImage, String username, String story
        for (int j=0;j<20;j++)
            story.add(new Story(j,1,image,"",image));
        stories.setAdapter(new StoriesAdapter(getContext(),story,getFragmentManager()));

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
                if (popular.getAlpha() == 1){
                    popular.setAlpha(0.5f);
                    news.setAlpha(1);
                    loadUserPosts();
                }
            }
        });
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
                if (news.getAlpha() == 1) {

                    popular_clicked = true;
                    popular.setAlpha(1);
                    news.setAlpha(0.5f);
                    loadPosts();

                }
            }
        });

        if (mPost.isEmpty()) {
            popular.setAlpha(1);
            news.setAlpha(0.5f);
            loadPosts();
        }


        if (!popular_clicked)
            loadUserPosts();

        return view;
    }

    private Helper helper() {
        return new Helper(getContext());
    }


    private void clean(){
        if (!mPost.isEmpty()) {
            mPost.clear();
            postsAdapter.notifyDataSetChanged();
        }
    }

    private void loadPosts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.POSTS_URL,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject posts = new JSONObject(response);
                    JSONArray jsonArray = posts.getJSONArray("posts");
                    for (int i=0;i<jsonArray.length();i++){
                        final JSONObject post = jsonArray.getJSONObject(i);

                        Posts dbPosts = new Posts(
                                post.getString("username"),
                                post.getString("post_location"),
                                post.getString("post_date") + " min",
                                post.getString("content"),
                                post.getInt("react"),
                                post.getInt("comments"),
                                post.getString("image"),
                                post.getString("picture"),
                                post.getInt("saved"),
                                post.getInt("id"),
                                post.getInt("user_id"));


                        mPost.add(dbPosts);
                     }

                    postsAdapter = new PostsAdapter(getContext(),mPost,getFragmentManager());
                    recyclerView.setAdapter(postsAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("POSTS",error.toString());
                helper().toast(error.toString());
//                textView.setText(error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);


    }
    private void loadUserPosts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.USER_POSTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject posts = new JSONObject(response);
                            JSONArray jsonArray = posts.getJSONArray("posts");
                            for (int i=0;i<jsonArray.length();i++){
                                final JSONObject post = jsonArray.getJSONObject(i);

                                Posts dbPosts = new Posts(
                                        post.getString("username"),
                                        post.getString("post_location"),
                                        post.getString("post_date") + " min",
                                        post.getString("content"),
                                        post.getInt("react"),
                                        post.getInt("comments"),
                                        post.getString("image"),
                                        post.getString("picture"),
                                        post.getInt("saved"),
                                        post.getInt("id"),
                                        post.getInt("user_id"));

                                mPost.add(dbPosts);
                            }
                            postsAdapter = new PostsAdapter(getContext(),mPost,getFragmentManager());
                            recyclerView.setAdapter(postsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("POSTS",error.toString());
                helper().toast(error.toString());
//                textView.setText(error.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void getUser(final int userID){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constants.POST_USER_DATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Posts posts = new Posts(jsonObject.getString("username"),jsonObject.getString("picture"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                helper().toast(error.toString());
                Log.d("APP_LOG",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("id", String.valueOf(userID));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
