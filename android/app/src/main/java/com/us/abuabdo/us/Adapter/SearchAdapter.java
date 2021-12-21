package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.Model.UsersSearch;
import com.us.abuabdo.us.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context mContext;
    private List<UsersSearch> mUsers;
    private FragmentManager mFragmentManager;

    public SearchAdapter(Context context, List<UsersSearch> UsersSearch,FragmentManager fragmentManager) {
        this.mContext = context;
        this.mUsers = UsersSearch;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_search_item,parent,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final UsersSearch user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        holder.fullName.setText(user.getBio());
        Picasso.with(mContext).load(user.getImage()).into(holder.profilePicture);




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                Bundle bundle =new Bundle();
                FragmentManager fragmentManager = ((AppCompatActivity)mContext).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, profileFragment);
                fragmentTransaction.commit();

                bundle.putInt("userID",user.getID());
                profileFragment.setArguments(bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public TextView fullName;
        public CircleImageView profilePicture;
        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.profile_username);
            fullName = itemView.findViewById(R.id.full_name);
            profilePicture = itemView.findViewById(R.id.user_img);

        }
    }
}
