package com.us.abuabdo.us.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.us.abuabdo.us.Adapter.HomeAdapter;
import com.us.abuabdo.us.Adapter.Posts;
import com.us.abuabdo.us.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private HomeAdapter homeAdapter;
    private List<Posts> mPost;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.posts_re);

        mPost = new ArrayList<>();

        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));
        mPost.add(new Posts("Mohamed","hi All","ksdkksdkkkjkdfkdfmkdf,",R.mipmap.dark_blue));

        homeAdapter = new HomeAdapter(getContext(),mPost);
        recyclerView.setAdapter(homeAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }


}
