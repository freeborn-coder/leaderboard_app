package com.gads2020.leaderboardapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;
import com.gads2020.leaderboardapp.Models.UserData;
import com.gads2020.leaderboardapp.R;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.LearnersViewHolder> {

    ArrayList<UserData> userDataArrayList;

    public LearnersRecyclerAdapter(ArrayList<UserData> userDataArrayList) {
        this.userDataArrayList = userDataArrayList;
    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);
        return new LearnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersViewHolder holder, int position) {
        // bind user data
        UserData userData = userDataArrayList.get(position);
        holder.bindUserData(userData);
    }

    @Override
    public int getItemCount() {
        return userDataArrayList.size();
    }


    public class LearnersViewHolder extends RecyclerView.ViewHolder{
        ImageView badgeImage;
        TextView nameTv, descriptionTv;

        public LearnersViewHolder(@NonNull View itemView) {
            super(itemView);
            badgeImage = itemView.findViewById(R.id.badge_image);
            nameTv = itemView.findViewById(R.id.name_tv);
            descriptionTv = itemView.findViewById(R.id.description_tv);
        }

        public void bindUserData(UserData userData) {
            nameTv.setText(userData.getName());

            Glide.with(itemView.getContext())
                    .load(userData.getBadgeUrl())
                    .into(badgeImage);

            String country = userData.getCountry();
            String description = "";
            if(userData instanceof TopScorerData){
                int score = ((TopScorerData)userData).getScore();
                description = score +" skill IQ Score, " + country;

            }else if(userData instanceof TopLearnerData){
                int hours = ((TopLearnerData)userData).getHours();
                description = hours + " learning hours, "+country;
            }
            descriptionTv.setText(description);
        }
    }
}
