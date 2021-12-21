package com.us.abuabdo.us.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.us.abuabdo.us.Adapter.SearchAdapter;
import com.us.abuabdo.us.Home;
import com.us.abuabdo.us.Model.Users;
import com.us.abuabdo.us.Model.UsersSearch;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Search;
import com.us.abuabdo.us.Stories;
import com.us.abuabdo.us.Vendor.Constants;
import com.us.abuabdo.us.Vendor.Helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    private List<UsersSearch> mUsersSearch;
    EditText searchBox;
    CharSequence charSequence;
    float x1,x2,y1, y2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_search);
        ImageView back = view.findViewById(R.id.back_search);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().finish();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
            }
        });

        searchBox = view.findViewById(R.id.search_box);
        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH){
                    search(charSequence);
                }
                return false;
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mUsersSearch = new ArrayList<>();

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                helper().toast(charSequence.toString());
            }

            @Override
            public void onTextChanged(final CharSequence searchValue, int i, int i1, int i2) {

                if (searchValue.length() != 0){
                    charSequence = searchValue;
                    search(searchValue);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        return view;
    }


    private Helper helper() {
        return new Helper(getActivity());
    }


    private void search(final CharSequence charSequence){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SEARCH, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("users");

                    for (int i=0;i < jsonArray.length(); i ++){
                        final JSONObject user = jsonArray.getJSONObject(i);
                        UsersSearch usersSearch = new UsersSearch(
                                user.getInt("id"),
                                user.getString("username"),
                                "bio",
                                user.getString("picture"));
                        mUsersSearch.add(usersSearch);
                    }

                    recyclerView.setAdapter(new SearchAdapter(getContext(),mUsersSearch, getFragmentManager()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("APP_LOG",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("type","users");
                params.put("search_key",charSequence.toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public boolean onTouchEvent(MotionEvent touchEvent){
        switch (touchEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchEvent.getX();
                y1 = touchEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchEvent.getX();
                y2 = touchEvent.getY();
                if (x1<x2)
                    startActivity(new Intent(getContext(), Home.class));
                else if (x1>x2)
                    startActivity(new Intent(getContext(), Stories.class));
                break;
        }
        return false;
    }



}
