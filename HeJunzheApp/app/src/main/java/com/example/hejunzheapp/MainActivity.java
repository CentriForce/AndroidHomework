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
import com.tencent.mmkv.MMKV;

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

        MMKV.initialize(this);
    }

    private void initView() {
        viewPager2 = findViewById(R.id.view_pager);
        navigationItemView = new NavigationItemView[4];
        navigationItemView[0] = findViewById(R.id.goto_page1);
        navigationItemView[1] = findViewById(R.id.goto_page2);
        navigationItemView[2] = findViewById(R.id.goto_page3);
        navigationItemView[3] = findViewById(R.id.goto_page4);
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

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < navigationItemView.length; i++) {
                    navigationItemView[i].setColor(getResources().getColor(R.color.black));
                }
                navigationItemView[position].setColor(getResources().getColor(R.color.blue));
            }
        });
    }
}