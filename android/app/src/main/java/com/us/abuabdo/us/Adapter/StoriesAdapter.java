package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.Model.Story;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Stories;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder> {


    Context mContext;
    List<Story> mStories;
    FragmentManager mFragmentManager;

    public StoriesAdapter(Context mContext, List<Story> mStories, FragmentManager mFragmentManager) {
        this.mContext = mContext;
        this.mStories = mStories;
        this.mFragmentManager = mFragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.stories_item,parent,false);
        return new StoriesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Story stories = mStories.get(position);


        Picasso.with(mContext).load(stories.getUserImage()).into(holder.storyImage);

        holder.storyUsername.setText(stories.getUsername());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent storey = new Intent(mContext, Stories.class);
                storey.putExtra("userID",stories.getUserID());
                mContext.startActivity(storey);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {



                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mStories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView storyImage;
        TextView storyUsername;
        public ViewHolder(View itemView) {
            super(itemView);

            storyImage = itemView.findViewById(R.id.storyImage);
            storyUsername = itemView.findViewById(R.id.storyUsername);

        }
    }
}
