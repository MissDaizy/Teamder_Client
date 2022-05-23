package com.example.teamder.ui.Group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamder.R;
import com.example.teamder.models.InstanceOfTypeGroup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.AppViewHolder> {

    List<InstanceOfTypeGroup> listOfGroups = new ArrayList<>();

    private LayoutInflater layoutInflater;
    private OnNoteListener recyclerOnNoteListener;

    public GroupAdapter(List<InstanceOfTypeGroup> _data, OnNoteListener onNoteListener){
        listOfGroups = _data;
        this.recyclerOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //onCreateViewHolder = what we will see in the recycler view


        layoutInflater = LayoutInflater.from(parent.getContext());

        //this value is passed to the view holder and getGroupsAmount
        // group is used to create the uis for the given array
        View view = layoutInflater.inflate(R.layout.list_group_row, parent, false);
        return new AppViewHolder(view, recyclerOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        /*
        TODO DIANCHIK's: when connecting to DB:
        String groupName = listOfGroups[position].getGroupName();
        holder.list_LBL_groupName.setText(groupName);

         */
    }

    @Override
    public int getItemCount() {
        //TODO DIANCHIK: //return listOfGroups.length;
        return 15;
    }

    public class AppViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        AppCompatImageView list_group_groupImage;
        MaterialTextView list_LBL_groupName;
        MaterialTextView liqueur_LBL_groupTopic;
        MaterialButton list_BTN_viewGroupBTN;
        OnNoteListener onNoteListener;


        public AppViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            list_group_groupImage=itemView.findViewById(R.id.list_group_groupImage);
            list_LBL_groupName=itemView.findViewById(R.id.list_LBL_groupName);
            liqueur_LBL_groupTopic=itemView.findViewById(R.id.liqueur_LBL_groupTopic);
            list_BTN_viewGroupBTN=itemView.findViewById(R.id.list_BTN_viewGroupBTN);

            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
