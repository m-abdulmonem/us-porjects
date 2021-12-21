package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.us.abuabdo.us.Fragment.Notificationfragment;
import com.us.abuabdo.us.Fragment.PostFragment;
import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.Model.Posts;
import com.us.abuabdo.us.Model.Story;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    public Context mContext;
    public List<Posts> mPosts;
    FragmentManager mFragmentManger;

    public PostsAdapter(Context mContext, List<Posts> mPosts, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.mPosts = mPosts;
        this.mFragmentManger = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_content,parent,false);

        return new PostsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Posts posts = mPosts.get(position);
        if (posts.getDescription().equals(""))
            holder.description.setVisibility(View.GONE);
        else{
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(posts.getDescription());
        }

        holder.username.setText(posts.getUsername());
        holder.city.setText(posts.getLocation());
        holder.date.setText(posts.getDate());
        // React Counts
        if (posts.getLoves() >= 1000)
            holder.likes_count.setText((posts.getLoves() / 1000) + R.string.k);
        else if (posts.getLoves() >= 1000000)
            holder.likes_count.setText((posts.getLoves() / 1000000) + R.string.m);
        else
            holder.likes_count.setText(posts.getLoves() +"");
        // Comments Count
        if (posts.getComment() >= 1000)
            holder.likes_count.setText(posts.getComment() / 1000 + R.string.k);
        else if (posts.getComment() >= 1000000)
            holder.likes_count.setText((posts.getComment() / 1000000) + R.string.m);
        else
            holder.likes_count.setText(posts.getComment() +"");
        // Saved
        if (posts.getSaved() == 1)
            holder.saved.setImageResource(R.drawable.ic_saved_full);
        else
            holder.saved.setImageResource(R.drawable.ic_saved);

        if (posts.getComment() > 0)
            holder.comment.setImageResource(R.drawable.ic_comment_full);
        if (posts.getLoves() > 0)
            holder.love.setImageResource(R.drawable.ic_like_full);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = mFragmentManger.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new Notificationfragment());
                fragmentTransaction.commit();
            }
        });

        holder.saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posts.getSaved() == 1)
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.POST_UPDATE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("APP_LOG",response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                jsonObject.getString("error");
                                if (jsonObject.getBoolean("success")) {
                                    holder.saved.setImageResource(R.drawable.ic_saved);
                                    helper().toast("Post unsaved");
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.d("APP_LOG",error.toString());
                            helper().toast(error.toString());
                        }
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("post_update_save", posts.getPostID() + "");
                            params.put("post_id", posts.getPostID() + "");
                            params.put("saved", 0+"");
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                    requestQueue.add(stringRequest);
                } else{
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.POST_UPDATE, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("APP_LOG",response);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getString("error").equals(""))
                                    helper().toast(jsonObject.getString("error"));
                                if (jsonObject.getBoolean("success")) {
                                    holder.saved.setImageResource(R.drawable.ic_saved_full);
                                    helper().toast("post saved");
                                }
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
                    })
                    {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<>();
                            params.put("post_update_save", String.valueOf(posts.getPostID()));
                            params.put("post_id", String.valueOf(posts.getPostID()));
                            params.put("saved", String.valueOf(1));
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(mContext);
                    requestQueue.add(stringRequest);
                }
            }
        });


        holder.user_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storey = new Intent(mContext, Story.class);
                storey.putExtra("userID",posts.getUserID());
                mContext.startActivity(storey);
            }
        });

        holder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle =new Bundle();
                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, profileFragment);
                fragmentTransaction.commit();

                bundle.putInt("userID",posts.getUserID());
                profileFragment.setArguments(bundle);
            }
        });


        holder.post_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostFragment postFragment = new PostFragment();
                Bundle bundle =new Bundle();
                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, postFragment);
                fragmentTransaction.commit();

                bundle.putString("image",posts.getImage());
                postFragment.setArguments(bundle);
            }
        });

        final int radius = 21;
        final int margin = 0;

        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.with(mContext).load(posts.getImage()).transform(transformation).into(holder.post_image);
        Picasso.with(mContext).load(posts.getUserImage()).transform(transformation).into(holder.user_picture);
//        Picasso.with(mContext).load(posts.getUserImage()).into(holder.user_picture);



    }


    private Helper helper() {
        return  new Helper(mContext);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView username,city,date,description,comment_count,likes_count;
        public ImageView post_image,love,comment,saved;
        public CircleImageView user_picture;
        public RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.post_username);
            city = itemView.findViewById(R.id.post_city);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);
            post_image = itemView.findViewById(R.id.post_img);
            love = itemView.findViewById(R.id.like);
            likes_count = itemView.findViewById(R.id.like_count);
            comment = itemView.findViewById(R.id.comment);
            comment_count = itemView.findViewById(R.id.comment_count);
            saved = itemView.findViewById(R.id.saved);
            user_picture = itemView.findViewById(R.id.user_picture);

            recyclerView = itemView.findViewById(R.id.stories_recycler_view);

        }
    }

}
