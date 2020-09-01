package com.gads2020.leaderboardapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.gads2020.leaderboardapp.Models.TopLearnerData;
import com.gads2020.leaderboardapp.Models.TopScorerData;
import com.gads2020.leaderboardapp.Models.UserData;

import static com.gads2020.leaderboardapp.Constants.TOP_LEARNERS_FRAGMENT;
import static com.gads2020.leaderboardapp.Constants.TOP_LEARNER_USER_ITEM;
import static com.gads2020.leaderboardapp.Constants.TOP_SCORER_USER_ITEM;

public class LeaderBoardFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        ArrayList<UserData> userList = null;
        int fragmentType = this.getArguments().getInt("type");

        if(this.getArguments() != null){
            if(fragmentType == TOP_LEARNERS_FRAGMENT)
                userList = getUserDataList(TOP_LEARNER_USER_ITEM);
            else{
                userList = getUserDataList(TOP_SCORER_USER_ITEM);
            }
        }
        LearnersRecyclerAdapter recyclerAdapter = new LearnersRecyclerAdapter(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);
    }


    // Get static data from Strings resources
    private ArrayList<UserData> getUserDataList(int itemType) {
        boolean isTopLearnerUserItem = itemType == TOP_LEARNER_USER_ITEM;
        ArrayList<UserData> list = new ArrayList<>();

        String dataString;
        if(isTopLearnerUserItem) {
            dataString = "";
        }else{
            dataString = "";
        }

        Log.d(getActivity().getPackageName(),dataString);

        JSONArray array;
        try {
            array = new JSONArray(dataString);

            for(int i = 0; i<array.length(); i++) {
                JSONObject jsonObject = array.getJSONObject(i);
                if(isTopLearnerUserItem){
                    TopLearnerData userItem = new TopLearnerData();
                    userItem.setName(jsonObject.getString("name"));
                    userItem.setCountry(jsonObject.getString("country"));
                    userItem.setHours(jsonObject.getInt("hour"));
                    list.add(userItem);
                }else{
                    TopScorerData userItem = new TopScorerData();
                    userItem.setName(jsonObject.getString("name"));
                    userItem.setCountry(jsonObject.getString("country"));
                    userItem.setScore(jsonObject.getInt("hour"));
                    userItem.setBadgeUrl(jsonObject.getString("badgeUrl"));
                    list.add(userItem);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(getActivity().getPackageName(),list.toString());
        return list;
    }
}
