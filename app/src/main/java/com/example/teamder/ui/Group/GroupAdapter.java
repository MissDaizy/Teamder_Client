package com.example.teamder.ui.Group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.MyViewHolder> {

    Context context;
    ArrayList<InstanceOfTypeGroup> allGroups;

    public GroupAdapter(Context context, ArrayList<InstanceOfTypeGroup> allGroups){

    }

    @NonNull
    @Override
    public GroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Here : giving look to the rows
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_group_row, parent, false);
        return new GroupAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.MyViewHolder holder, int position) {
        // what data to update in each views (16:13)
        /*
        TODO: DIANCHIK's
        holder.list_LBL_name.(allGroups.get(position).getName());
        same for:
        list_group_photo & liqueur_LBL_topic

         */
    }

    @Override
    public int getItemCount() {
        //the recycler view >> number of items to display

        return allGroups.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //similar to on create method, Grabbing the views

        AppCompatImageView list_group_photo;
        MaterialTextView list_LBL_name, liqueur_LBL_topic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            list_group_photo=itemView.findViewById(R.id.list_group_photo);
            list_LBL_name=itemView.findViewById(R.id.list_LBL_name);
            liqueur_LBL_topic=itemView.findViewById(R.id.liqueur_LBL_topic);
        }
    }
}





