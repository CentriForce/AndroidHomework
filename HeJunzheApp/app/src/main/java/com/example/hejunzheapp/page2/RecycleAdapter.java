package com.example.hejunzheapp.page2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hejunzheapp.MainActivity;
import com.example.hejunzheapp.R;

import java.util.Date;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private final List<Page2Item> items;
    private final RecycleAdapter.onItemClickListener onItemClickListener;

    public RecycleAdapter(List<Page2Item> items,onItemClickListener onItemClickListener) {
        this.items = items;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_recycle_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // 绑定数据到ViewHolder的View上
        Page2Item item = items.get(position);
        holder.textView.setText(item.getTitle());
        // 计算距离结束的天数
        Date end = item.getDay();
        Date now = new Date();
        long timeDifferenceInDays = (end.getTime() - now.getTime()) / 1000 / 60 / 60 / 24 + 1;
        holder.dayView.setText(String.valueOf(timeDifferenceInDays));
        // 设置item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null){
                    onItemClickListener.onItemClick(position);
                }
            }
        });
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

    public interface onItemClickListener{
        void onItemClick(int position);
    }
}
