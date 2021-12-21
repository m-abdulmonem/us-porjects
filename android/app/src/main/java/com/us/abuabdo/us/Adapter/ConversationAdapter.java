package com.us.abuabdo.us.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.us.abuabdo.us.Chat;
import com.us.abuabdo.us.Fragment.ConversationFragment;
import com.us.abuabdo.us.Fragment.ProfileFragment;
import com.us.abuabdo.us.Model.Conversation;
import com.us.abuabdo.us.R;
import com.us.abuabdo.us.Stories;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {

    private Context mContext;
    private List<Conversation> mConversation;
    private FragmentManager mFragmentManager;
    public ConversationAdapter(Context mContext, List<Conversation> mConversation, FragmentManager fragmentManager) {
        this.mContext = mContext;
        this.mConversation = mConversation;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.conversation,parent,false);

        return new ConversationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Conversation conversation = mConversation.get(position);

        Picasso.with(mContext).load(conversation.getConversation_image()).into(holder.conversation_user_pic);
        holder.conversation_username.setText(conversation.getConversation_username());
        holder.conversation_last_message.setText(conversation.getConversation_message());
        holder.conversation_message_count.setText(conversation.getConversation_msg_count());
        holder.conversation_date.setText(conversation.getConversation_date());

        holder.conversation_user_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, Stories.class));
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chat = new Intent(mContext, Chat.class);
                chat.putExtra("userID",conversation.getID());
                mContext.startActivity(chat);
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
        return mConversation.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView conversation_username,conversation_last_message,conversation_date,conversation_message_count;
        CircleImageView conversation_user_pic;
        public ViewHolder(View itemView) {
            super(itemView);
            conversation_username = itemView.findViewById(R.id.conversation_username);
            conversation_last_message = itemView.findViewById(R.id.conversation_last_message);
            conversation_date = itemView.findViewById(R.id.conversation_date);
            conversation_message_count = itemView.findViewById(R.id.conversation_message_count);
            conversation_user_pic = itemView.findViewById(R.id.conversation_user_pic);
        }
    }
}
