package com.example.hejunzheapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hejunzheapp.customview.NavigationItemView;
import com.example.hejunzheapp.viewpager.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;

    private NavigationItemView[] navigationItemView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initView();

        setViewPager();
    }

    private void initView() {
        viewPager2 = findViewById(R.id.view_pager);
        navigationItemView = new NavigationItemView[3];
        navigationItemView[0] = findViewById(R.id.goto_page1);
        navigationItemView[1] = findViewById(R.id.goto_page2);
        navigationItemView[2] = findViewById(R.id.goto_page3);
    }

    private void setViewPager() {
        viewPager2.setAdapter(new ViewPagerAdapter(this));

        for (int i = 0; i < navigationItemView.length; i++) {
            int finalI = i;
            navigationItemView[finalI].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewPager2.setCurrentItem(finalI, true);
                }
            });
        }
    }
}