package com.example.teamder.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class TagsCreator {
    // Tags
    private AlertDialog tagsDialog;
    private AlertDialog.Builder tagsBuilder;
    private final CharSequence[] tagsList =
            {"Drawing", "sculpture", "Soldering",
                    "Book Readers", "Business",
                    "aerobic", "Anaerobic",
                    "Photographers", "Catering", "Halls", "Make Up", "Hair Stylist",
                    "Java", "Python", "C", "React.js",
                    "DJ", "Classic Music", "Rock Music",
                    "Meditation", "Yoga", "Breathing Meditation",
                    "Morning Runners", "Evening Runners",
                    "Mountaineering", "Stream Trip",
                    "Nature Photography", "Animal Photography", "Couple Photography", "Portrait Photography"};
    private final ArrayList selectItems = new ArrayList();

    public TagsCreator() {
    }

    // When set listener:
    public void showTags(Context context, TextView textView) {
        // Init alertdialog builder
        tagsBuilder = new AlertDialog.Builder(context);
        tagsBuilder.setTitle("Your Interests").setMultiChoiceItems(tagsList, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    // Add to selected items
                    selectItems.add(tagsList[position]);
                }else if (existInList()){
                    // TODO: need fixing::: If the Item is already in the list >> delete from selected items
                    selectItems.remove(Integer.valueOf(position));
                }

            }
        });

        // Show Tags in TextView
        printChoosenTags(textView);

        // Create Dialog
        tagsDialog = tagsBuilder.create();
        tagsDialog.show();
    }

    private boolean existInList(){
        boolean exist = false;
        for(int i=0; i < tagsList.length; i++) {
            if(selectItems.equals(tagsList[i])){
                exist = true;
            }
        }

        return exist;
    }

    private void printChoosenTags(TextView textView) {
        // Show Tags in TextView
        tagsBuilder.setPositiveButton("Selected Items", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder sb = new StringBuilder();
                for (Object tagsList:selectItems){
                    sb.append(tagsList.toString() + "\n");
                }
                textView.setText(sb.toString());
            }
        });
    }

}
