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
import com.us.abuabdo.us.Model.Posts;
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
//
        Posts posts = mPosts.get(position);
        holder.post_username.setText(posts.getUsername());
        holder.post_city.setText(posts.getLocation());
//        holder.date.setText(posts.getDate());
//        holder.location.setText(posts.getLocation());
//        holder.description.setText(posts.getDescription());
//        holder.like_count.setText(posts.getLoves() + " " + R.string.likes);
//        holder.comment_counts.setText(posts.getComment() + " " + R.string.comment);

//        final Users user = mPosts.get(position);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ((FragmentActivity)mContext).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView username,date,location,username_in_caption,description,like_count,comment_counts;
//        CircleImageView user_pic_post,user_picture_comment;
//        ImageView post,like,comment,saved;

        TextView post_username,post_city;
        public ViewHolder(View itemView) {
            super(itemView);

            post_username = itemView.findViewById(R.id.post_username);
            post_city = itemView.findViewById(R.id.post_city);
//            location = itemView.findViewById(R.id.city);
//            username_in_caption = itemView.findViewById(R.id.username_in_caption);
//            description = itemView.findViewById(R.id.description);
//            like_count = itemView.findViewById(R.id.loves);
//            comment_counts = itemView.findViewById(R.id.comment_count);
////            user_pic_post = itemView.findViewById(R.id.user_picture);
////            user_picture_comment = itemView.findViewById(R.id.user_picture_comment);
//            post = itemView.findViewById(R.id.post);
//            like = itemView.findViewById(R.id.like);
//            comment = itemView.findViewById(R.id.comment);
//            saved = itemView.findViewById(R.id.saved);
        }
    }
}
