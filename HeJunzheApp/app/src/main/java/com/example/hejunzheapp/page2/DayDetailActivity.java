package com.example.hejunzheapp.page2;

import static com.example.hejunzheapp.KEYS.DATE_KEY;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hejunzheapp.R;
import com.tencent.mmkv.MMKV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DayDetailActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day_detail);

        // initview
        TextView showDateTextView = findViewById(R.id.show_date_textview);
        TextView showDetailTextView = findViewById(R.id.show_detail_textview);
        TextView showTitleTextView = findViewById(R.id.show_title_textview);
        TextView remainDateTextView = findViewById(R.id.remain_date_textview);
        TextView deleteButton = findViewById(R.id.delete_button);

        int position = getIntent().getIntExtra("position", -1);
        if (position == -1) {
            finish();
        } else {
            ArrayList<Page2Item> page2Items = Page2Item.LoadPage2Item();
            Page2Item presentDetail = page2Items.get(position);

            String title = presentDetail.getTitle();
            String detail = presentDetail.getDetail();
            Date end = presentDetail.getDay();

            showTitleTextView.setText(title);
            showDetailTextView.setText(detail);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            showDateTextView.setText(dateFormat.format(end));

            Date now = new Date();
            long timeDifferenceInDays = (end.getTime() - now.getTime()) / 1000 / 60 / 60 / 24 + 1;
            remainDateTextView.setText("距离倒数日结束还有"+timeDifferenceInDays+"天");
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Page2Item.DeletePage2Item(position);
                finish();
            }
        });
    }
}
