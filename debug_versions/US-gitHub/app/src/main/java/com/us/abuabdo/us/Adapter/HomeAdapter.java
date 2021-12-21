package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context mContext;
    private List<Posts> mPosts;

    public HomeAdapter(Context mContext, List<Posts> mPosts) {
        this.mContext = mContext;
        this.mPosts = mPosts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.post_content,parent,false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.post_user_name.setText(mPosts.get(position).getName());
        holder.post_content.setText(mPosts.get(position).getContent());
        holder.post_date.setText(mPosts.get(position).getContent());
        holder.post_user_image.setImageResource(mPosts.get(position).getImage());

//        final Users user = mPosts.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView post_user_name;
        public TextView post_content;
        public TextView post_date;
        public ImageView post_user_image;
        public ViewHolder(View itemView) {
            super(itemView);

            post_user_name = itemView.findViewById(R.id.post_user_name);
            post_content = itemView.findViewById(R.id.post_content);
            post_date = itemView.findViewById(R.id.post_date);
            post_user_image = itemView.findViewById(R.id.post_user_image);
        }
    }
}
