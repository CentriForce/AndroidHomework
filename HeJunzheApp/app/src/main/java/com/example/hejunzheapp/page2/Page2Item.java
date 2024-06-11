package com.example.hejunzheapp.page2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import java.util.ArrayList;
import java.util.Date;

public class Page2Item {
    private String title;
    private static final String DATE_KEY = "date_key";
    private String detail;
    // TODO 添加日期变量 和对应的 getter 和对应的 构造方法
    private Date day;

    public Page2Item(String title, Date day) {
        this.title = title;
        this.day = day;
    }

    public Page2Item(String title, Date day, String detail) {
        this.title = title;
        this.day = day;
        this.detail = detail;
    }

    public static String SavePage2Item(Page2Item item) {
        // 从MMKV中获取保存的数据
        ArrayList<Page2Item> saveData = new Gson().fromJson(
                MMKV.defaultMMKV()
                        .decodeString(DATE_KEY),
                new TypeToken<ArrayList<Page2Item>>() {
                }.getType()
        );
        // 如果保存的数据为空，则创建一个新的ArrayList
        if (saveData == null) {
            saveData = new ArrayList<Page2Item>();
        }
        saveData.add(item);
        // 将数据转换为json
        Gson gson = new Gson();
        String jsonString = gson.toJson(saveData);
        // 保存数据到 MMKV
        MMKV.defaultMMKV().encode(DATE_KEY, jsonString);
        return jsonString;
    }

    public static ArrayList<Page2Item> LoadPage2Item() {
        ArrayList<Page2Item> loadData = new Gson().fromJson(
                MMKV.defaultMMKV()
                        .decodeString(DATE_KEY),
                new TypeToken<ArrayList<Page2Item>>() {
                }.getType()
        );
        return loadData;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
