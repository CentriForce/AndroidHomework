package com.example.hejunzheapp.page2;

import java.util.Calendar;

public class Page2Item {
    private String title;
    // TODO 添加日期变量 和对应的 getter 和对应的 构造方法
    private Calendar day;

    public Page2Item(String title, Calendar day) {
        this.title = title;
        this.day = day;
    }

    public Calendar getDay() {
        return day;
    }

    public void setDay(Calendar day) {
        this.day = day;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
