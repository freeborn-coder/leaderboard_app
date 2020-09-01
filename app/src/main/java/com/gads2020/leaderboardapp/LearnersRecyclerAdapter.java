package com.gads2020.leaderboardapp;

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

import static com.gads2020.leaderboardapp.Constants.TOP_LEARNER_USER_ITEM;
import static com.gads2020.leaderboardapp.Constants.TOP_SCORER_USER_ITEM;

public class LearnersRecyclerAdapter extends RecyclerView.Adapter<LearnersRecyclerAdapter.LearnersViewHolder> {

    ArrayList<UserData> userDataArrayList;

    public LearnersRecyclerAdapter(ArrayList<UserData> userDataArrayList) {
        this.userDataArrayList = userDataArrayList;
    }

    @NonNull
    @Override
    public LearnersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_layout,parent,false);
        ImageView badgeImage = view.findViewById(R.id.badge_image);

        if(viewType == TOP_SCORER_USER_ITEM){
            Glide.with(parent.getContext())
                    .load(R.drawable.skill_iq_badge)
                    .into(badgeImage);
        }else if(viewType == TOP_LEARNER_USER_ITEM){
            Glide.with(parent.getContext())
                    .load(R.drawable.top_learner)
                    .into(badgeImage);
        }
        return new LearnersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnersViewHolder holder, int position) {
        // bind user data
        UserData userData = userDataArrayList.get(position);
        holder.nameTv.setText(userData.getName());

        String country = userData.getCountry();
        String description = "";
        if(userData instanceof TopScorerData){
            int score = ((TopScorerData)userData).getScore();
            description = score +" skill IQ Score, " + country;

        }else if(userData instanceof TopLearnerData){
            int hours = ((TopLearnerData)userData).getHours();
            description = hours + "learning hours, "+country;
        }
        holder.descriptionTv.setText(description);
    }

    @Override
    public int getItemCount() {
        return userDataArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        UserData userData = userDataArrayList.get(position);
        if(userData instanceof TopLearnerData){
            return TOP_LEARNER_USER_ITEM;
        }
        else if(userData instanceof TopScorerData){
            return TOP_SCORER_USER_ITEM;
        }
        return position;
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
    }
}
