package com.us.abuabdo.us.Fragment;


import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.us.abuabdo.us.Adapter.MusicAdapter;
import com.us.abuabdo.us.Adapter.MusicFashionAdapter;
import com.us.abuabdo.us.Model.Music;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Search;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MusicFragment extends Fragment {

    ImageView search;
    RecyclerView music_new_album_recyclerView,recent_fashion_recycler_View;
    List<Music> mMusic;
    TextView recommended,sdCard;
    MusicAdapter musicAdapter;
    boolean recommendedClicked;
    String[] items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        search = view.findViewById(R.id.search_music);
        recommended = view.findViewById(R.id.recommended);
        sdCard = view.findViewById(R.id.sd_card);

        music_new_album_recyclerView = view.findViewById(R.id.music_new_album_recyclerView);
        music_new_album_recyclerView.setHasFixedSize(true);
        music_new_album_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.HORIZONTAL,false));
        mMusic = new ArrayList<>();
        String image = "https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
       for (int i = 0; i < 10; i++)
        mMusic.add(new Music("Try Me " + i,"19 Mars 2019", image,true));

        musicAdapter= new MusicAdapter(getContext(),mMusic,getFragmentManager());
        music_new_album_recyclerView.setAdapter(musicAdapter);




        recent_fashion_recycler_View = view.findViewById(R.id.recent_fashion_recycler_View);
        recent_fashion_recycler_View.setHasFixedSize(true);
        recent_fashion_recycler_View.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayout.HORIZONTAL,false));
        List<Music> music = new ArrayList<>();
        for (int j = 0; j <5; j++)
            music.add(new Music("Try Me " + j,"19 Mars 2019", image,true));
        recent_fashion_recycler_View.setAdapter(new MusicFashionAdapter(getContext(),music,getFragmentManager()));



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent searchPage = new Intent(getContext(), Search.class);
                searchPage.putExtra("page","music");
                startActivity(searchPage);
            }
        });



        recommended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                clean();
                if (sdCard.getAlpha() == 1){
                    sdCard.setAlpha(0.5f);
                    recommended.setAlpha(1);

                }
            }
        });
        sdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                clean();
                if (recommended.getAlpha() == 1) {

                    recommendedClicked = true;
                    sdCard.setAlpha(1);
                    recommended.setAlpha(0.5f);


                }
            }
        });

        runTimePermission();

        return view;
    }



    public ArrayList<File> getSongs(File file){
        ArrayList<File> songs = new ArrayList<>();
        File[] files = file.listFiles();

        for (File SingleFile : files){
            if (SingleFile.isDirectory() && !SingleFile.isHidden()){
                songs.addAll(getSongs(SingleFile));
            }else{
                if (SingleFile.getName().endsWith(".mp3") || SingleFile.getName().endsWith(".wav")){
                    songs.add(SingleFile);
                }
            }
        }

        return songs;
    }

    public void displaySongs(){
        ArrayList<File> songs = getSongs(Environment.getExternalStorageDirectory());

        items = new String[songs.size()];

        for (int i = 0; i < songs.size(); i++){
            items[i] = songs.get(i).getName().toString().replace("mp3","").replace("wav","");
        }
    }

    public void runTimePermission(){
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();
            }
        }).check();
    }

    private void clean(){
        if (!mMusic.isEmpty()) {
            mMusic.clear();
            musicAdapter.notifyDataSetChanged();
        }
    }

}
