package com.example.hejunzheapp.page2;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hejunzheapp.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewDayActivity extends AppCompatActivity {

    private static final String DATE_KEY = "date_key";
    private int year;
    private int month;
    private int day;
    private DatePicker datePickerView;
    private TextView selectDate;
    private EditText addTitle;
    private EditText addNote;
    private TextView confirmButton;
    private Date date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_day);

        selectDate = findViewById(R.id.select_date_textview);
        addTitle = findViewById(R.id.add_title_edittext);
        addNote = findViewById(R.id.add_note_edittext);
        confirmButton = findViewById(R.id.confirm_button);

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个日期选择器
                MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
                builder.setSelection(MaterialDatePicker.todayInUtcMilliseconds());
                MaterialDatePicker<Long> datePicker = builder.build();
                // 显示日期选择器
                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
                // 设置日期选择器的监听器
                datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
                    @Override
                    public void onPositiveButtonClick(Long l) {
                        date = new Date(l);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        selectDate.setText(dateFormat.format(date));
                    }
                });
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断是否为空
                Boolean ifDateEmpty = selectDate.getText().toString().isEmpty();
                Boolean ifTitleEmpty = addTitle.getText().toString().isEmpty();
                Boolean ifNoteEmpty = addNote.getText().toString().isEmpty();
                if (ifDateEmpty || ifTitleEmpty || ifNoteEmpty) {
                    Toast.makeText(getApplicationContext(), "请填写完整信息！", Toast.LENGTH_SHORT).show();
                    return;
                }

                Page2Item page2Item = new Page2Item(
                        addTitle.getText().toString(),
                        date,
                        addNote.getText().toString()
                );

                String jsonString = Page2Item.SavePage2Item(page2Item);

                System.out.println("jsonString: " + jsonString);

                ArrayList<Page2Item> loadData = Page2Item.LoadPage2Item();
                if (loadData != null) {
                    for (Page2Item item : loadData) {
                        System.out.println("getTitle: " + item.getTitle());
                        System.out.println("getDay: " + item.getDay());
                        System.out.println("getDetail: " + item.getDetail());
                    }
                }
                finish();
            }
        });

    }
}
