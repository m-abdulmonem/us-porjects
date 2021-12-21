package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.Model.Music;
import com.us.abuabdo.us.R;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MusicFashionAdapter extends RecyclerView.Adapter<MusicFashionAdapter.ViewHolder> {


    private Context mContext;
    private List<Music> mMusic;
    private FragmentManager mFragmentManager;

    public MusicFashionAdapter(Context mContext, List<Music> mMusic, FragmentManager mFragmentManager) {
        this.mContext = mContext;
        this.mMusic = mMusic;
        this.mFragmentManager = mFragmentManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_recent_fahsion_item,parent,false);
        return  new MusicFashionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music = mMusic.get(position);

        Picasso.with(mContext).load(music.getAlbumImage()).transform(new RoundedCornersTransformation(21,0)).into(holder.recentImage);

    }

    @Override
    public int getItemCount() {
        return mMusic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView recentImage;
        public ViewHolder(View itemView) {
            super(itemView);
            recentImage = itemView.findViewById(R.id.recent_image);
        }
    }
}
