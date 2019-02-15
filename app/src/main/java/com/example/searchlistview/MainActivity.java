package com.example.searchlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerItems;
    TextView queryNama;
    List<String> data;
    ItemsAdapter itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerItems = findViewById(R.id.recyclerItems);
        queryNama = findViewById(R.id.queryNama);

        data = new ArrayList<>();
        itemsAdapter = new ItemsAdapter(this);
        data.add("Muhammad Nur Rachman");
        data.add("Rachman Nur");
        data.add("M Nur Rachman");
        data.add("Muhammad Nur");
        data.add("Sean");
        data.add("Novan");

        itemsAdapter.addListItems(data);
        recyclerItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerItems.setAdapter(itemsAdapter);

        queryNama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                itemsAdapter.getFilter().filter(s.toString());
            }
        });
    }
}
