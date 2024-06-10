package com.example.hejunzheapp.page2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hejunzheapp.R;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private final List<Page2Item> items;

    public RecycleAdapter(List<Page2Item> items) {
        this.items = items;
    }

    // 计算两个日期之间的天数差
    private static long getDaysBetween(Calendar start, Calendar end) {
        long diffInMs = end.getTimeInMillis() - start.getTimeInMillis();
        return TimeUnit.DAYS.convert(diffInMs, TimeUnit.MILLISECONDS);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 绑定数据到ViewHolder的View上
        Page2Item item = items.get(position);
        holder.textView.setText(item.getTitle());

        Calendar end = item.getDay();
        Calendar now = Calendar.getInstance();
        holder.dayView.setText(String.valueOf(getDaysBetween(now, end)));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public TextView dayView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 设置item的显示内容
            textView = itemView.findViewById(R.id.tv_title);
            dayView = itemView.findViewById(R.id.tv_day);
        }
    }
}
