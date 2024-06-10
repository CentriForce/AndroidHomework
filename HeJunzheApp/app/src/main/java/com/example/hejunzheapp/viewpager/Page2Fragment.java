package com.example.hejunzheapp.viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hejunzheapp.R;
import com.example.hejunzheapp.page2.Page2Item;
import com.example.hejunzheapp.page2.RecycleAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Page2Fragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Page2Item> items = new ArrayList<Page2Item>();
        for (int i = 0; i < 20; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(2024, 9, i);
            items.add(new Page2Item("距离10月的第" + (i + 1) + "天还有", calendar));
        }
        RecycleAdapter recycleAdapter = new RecycleAdapter(items);
        recyclerView.setAdapter(recycleAdapter);
    }
}