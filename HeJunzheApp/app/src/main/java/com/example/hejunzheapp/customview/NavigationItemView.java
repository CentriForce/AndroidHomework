package com.example.hejunzheapp.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hejunzheapp.R;

public class NavigationItemView extends LinearLayout {

    private ImageView imageView;
    private TextView textView;

    public NavigationItemView(Context context) {
        super(context);

        LayoutInflater.from(context).inflate(R.layout.view_navigation_item, this);

        initView();
    }

    public NavigationItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_navigation_item, this);

        initView();

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavigationItemView);

        String text = typedArray.getString(R.styleable.NavigationItemView_text);
        int imageId = typedArray.getResourceId(R.styleable.NavigationItemView_image,R.drawable.baseline_warning_amber_24);

        imageView.setImageResource(imageId);
        textView.setText(text);

        typedArray.recycle();
    }

    public NavigationItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NavigationItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setColor(int color) {
        imageView.setColorFilter(color);
        textView.setTextColor(color);
    }

    private void initView() {
        imageView = findViewById(R.id.navigation_item_image);
        textView = findViewById(R.id.navigation_item_text);
    }
    public String  getText(){
        return textView.getText().toString();
    }
}
