package com.example.mayankagarwal.thechatapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

/**
 * Created by Mayank Agarwal on 06-02-2018.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder>{

    private List<Messages> mMessageList;
    private FirebaseAuth mAuth;

    public MessageAdapter(List<Messages> mMessageList){
        this.mMessageList = mMessageList;

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_single_layout, parent, false);
        return new MessageViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int i) {

        String current_user_id = mAuth.getCurrentUser().getUid();
        Messages c = mMessageList.get(i);
        String from_user = c.getFrom();

        if (from_user.equals(current_user_id)){

            viewHolder.messageText.setBackgroundResource(R.drawable.message_text_background_sender);
            viewHolder.messageText.setTextColor(Color.BLACK);
            viewHolder.message_single_layout.setGravity(Gravity.RIGHT);

        }else{
            viewHolder.messageText.setBackgroundResource(R.drawable.message_text_background_reciever);
            viewHolder.messageText.setTextColor(Color.BLACK);
            viewHolder.message_single_layout.setGravity(Gravity.LEFT);
        }
        viewHolder.messageText.setText(c.getMessage());
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }


    public class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageText;
        private RelativeLayout message_single_layout;

        public MessageViewHolder(View view) {
            super(view);
            messageText = (TextView)view.findViewById(R.id.message_text_layout);
            message_single_layout = (RelativeLayout)view.findViewById(R.id.message_single_layout);
        }
    }
}
