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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.Model.Music;
import com.us.abuabdo.us.Model.UsersSearch;
import com.us.abuabdo.us.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MusicAdapter  extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private Context mContext;
    private List<Music> mUsers;
    private FragmentManager mFragmentManager;

    public MusicAdapter(Context context, List<Music> music,FragmentManager fragmentManager) {
        this.mContext = context;
        this.mUsers = music;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.new_album,parent,false);
        return new MusicAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MusicAdapter.ViewHolder holder, int position) {

        final Music user = mUsers.get(position);
        Picasso.with(mContext).load(user.getAlbumImage()).transform( new RoundedCornersTransformation(21, 0)).into(holder.album_image);
        holder.album_name.setText(user.getAlbumName());
        holder.album_date.setText(user.getAlbumDate());

        holder.album_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView album_image,album_love;
        TextView album_name,album_date;

        public ViewHolder(View itemView) {
            super(itemView);

            album_image = itemView.findViewById(R.id.album_image);
            album_name = itemView.findViewById(R.id.album_name);
            album_date = itemView.findViewById(R.id.album_date);
            album_love = itemView.findViewById(R.id.album_love);


        }
    }
}
