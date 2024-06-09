package com.example.hejunzheapp.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hejunzheapp.R;

public class NavigationItemView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    public NavigationItemView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.view_navigation_item,this);

        initView();


    }

    public void setColor(int color) {
        imageView.setColorFilter(color);
        textView.setTextColor(color);
    }

    private void initView() {
        imageView = findViewById(R.id.navigation_item_image);
        textView = findViewById(R.id.navigation_item_text);
    }
}
