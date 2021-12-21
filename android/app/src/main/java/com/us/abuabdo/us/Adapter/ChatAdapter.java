package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.us.abuabdo.us.Chat;
import com.us.abuabdo.us.Model.Message;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Vendor.Auth;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder>  {

    private int VIEW_TYPE_LEFT =0;
    private int VIEW_TYPE_RIGHT =1;
    private Context mContext;
    private List<Message> mChat;
    private int[] mUsernameColors;
    private FragmentManager mFragmentManager;

    public ChatAdapter(Context mContext, List<Message> mChat, FragmentManager mFragmentManager) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_RIGHT){
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_right,parent,false);
            return new ChatAdapter.ViewHolder(view);
        } else{
            View view = LayoutInflater.from(mContext).inflate(R.layout.message_left,parent,false);
            return new ChatAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mChat.get(position);
        holder.display_message.setText(message.getMessage());
    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView display_message;
        public ViewHolder(View itemView) {
            super(itemView);
            display_message = itemView.findViewById(R.id.display_message);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mChat.get(position).getSender() == auth().getID()){
            return VIEW_TYPE_RIGHT;
        }else{
            return VIEW_TYPE_LEFT;
        }
    }

    public Auth auth(){
        return new Auth(mContext);
    }
}
