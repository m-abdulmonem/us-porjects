package com.us.abuabdo.us.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.us.abuabdo.us.Adapter.ChatAdapter;
import com.us.abuabdo.us.Adapter.ConversationAdapter;
import com.us.abuabdo.us.Adapter.SearchAdapter;
import com.us.abuabdo.us.Model.Conversation;
import com.us.abuabdo.us.Model.UsersSearch;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;
import com.us.abuabdo.us.Vendor.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatFragment extends Fragment {


    RecyclerView recyclerView;
    private List<Conversation> mConversation;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);


        recyclerView = view.findViewById(R.id.conversations_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL,false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        mConversation = new ArrayList<>();

        getConversation();
        return view;
    }


    private void getConversation(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.CONVERSATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("APP_LOG",response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("conversation");

                    for (int i=0;i < jsonArray.length(); i ++){
                        final JSONObject conersation = jsonArray.getJSONObject(i);
                        Conversation usersSearch = new Conversation(
                             conersation.getInt("id"),
                             conersation.getString("picture"),
                             conersation.getString("username"),
                             "hello",
                             "5",
                             "26 min"
                        );
                        mConversation.add(usersSearch);
                    }

                    recyclerView.setAdapter(new ConversationAdapter(getContext(),mConversation, getFragmentManager()));
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
                params.put("id",String.valueOf(auth().getID()));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private Auth auth() {
        return new Auth(getContext());
    }

}
