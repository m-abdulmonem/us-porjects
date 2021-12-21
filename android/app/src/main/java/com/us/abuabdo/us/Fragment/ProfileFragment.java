package com.us.abuabdo.us.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Database.SingleTon;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProfileFragment extends Fragment {

    TextView username,photos,followers,following,info;
    CircleImageView picture;
    ImageView cover;
    private View view;
    private boolean requestComplated;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);


        username = view.findViewById(R.id.profile_username);
        photos = view.findViewById(R.id.photos);
        followers = view.findViewById(R.id.followers);
        following = view.findViewById(R.id.following);
        picture = view.findViewById(R.id.userProfilePicture);
        cover  = view.findViewById(R.id.cover);
        info   = view.findViewById(R.id.user_info);

        username.setText(auth().getName());
        photos.setText(String.valueOf(auth().getPhotos()));
        followers.setText(String.valueOf(auth().getFollowers()));
        following.setText(String.valueOf(auth().getFollowing()));
        info.setText(auth().getDate() + " Years, " +auth().getLocation());
        Picasso.with(getActivity()).load(auth().getPicture()).into(picture);
        Picasso.with(getContext()).load(auth().getCover()).into(cover);




        return view;

    }

    public int userID(){
        return getArguments().getInt("userID");
    }


    private Helper helper(){
        return new Helper(getContext());
    }
    private Auth auth(){
        return  new Auth(getContext());
    }

}
