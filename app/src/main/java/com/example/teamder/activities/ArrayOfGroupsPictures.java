package com.example.teamder.activities;

import android.widget.ImageView;

import com.example.teamder.R;

import java.util.ArrayList;

public class ArrayOfGroupsPictures {

    private int defaultNumberOfGroups = 10;
    private int[] pictures = {R.drawable.img_art,
            R.drawable.img_fitness,
            R.drawable.img_inspiration,
            R.drawable.img_music,
            R.drawable.img_meditation,
            R.drawable.img_photography,
            R.drawable.img_programming,
            R.drawable.img_running,
            R.drawable.img_traveling,
            R.drawable.img_weddings};

    public ArrayOfGroupsPictures(int defaultNumberOfGroups, int[] pictures) {
        this.defaultNumberOfGroups = defaultNumberOfGroups;
        this.pictures = pictures;
    }

    public int getDefaultNumberOfGroups() {
        return defaultNumberOfGroups;
    }

    public int[] getPictures() {
        return pictures;
    }

}
