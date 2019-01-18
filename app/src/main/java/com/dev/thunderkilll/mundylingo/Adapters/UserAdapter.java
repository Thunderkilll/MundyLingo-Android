package com.dev.thunderkilll.mundylingo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dev.thunderkilll.mundylingo.Models.User;
import com.dev.thunderkilll.mundylingo.R;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.MyViewHolderUser> {
private Context context;
public List<User> leadersList;

public class MyViewHolderUser extends RecyclerView.ViewHolder {
    public ImageView imageUser;
    public TextView user_name;
    public TextView user_score;



    public MyViewHolderUser(View view) {
        super(view);
        imageUser = view.findViewById(R.id.user_img);
        user_name = view.findViewById(R.id.user_name);
        user_score = view.findViewById(R.id.user_score);

        //appel au cadnat
    }


}

    public UserAdapter(Context context, List<User> leadersList) {
        this.context = context;
        this.leadersList = leadersList;
    }

    @Override
    public MyViewHolderUser onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lead_item_row, parent, false);

        return new MyViewHolderUser(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUser myViewHolderUser, final int position) {
        //our code
        final  User u = leadersList.get(position);

        myViewHolderUser.user_name.setText(u.getUsername());
        myViewHolderUser.user_score.setText(u.getScoreEng());
        Glide.with(context)
                .load(u.getImgUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(myViewHolderUser.imageUser);

    }

    @Override
    public int getItemCount() {
        return leadersList.size();
    }


}
