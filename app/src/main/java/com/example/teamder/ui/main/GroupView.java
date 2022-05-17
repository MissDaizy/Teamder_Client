package com.example.teamder.ui.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/*
Classes GroupActivity and GroupView
is for dynamic setting images as matrix
for Screen "HOME"
 */

public class GroupView extends View {

    public GroupView(@Nullable Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GroupView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }
}

