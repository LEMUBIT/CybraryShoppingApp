package com.lemubit.lemuel.cybraryshoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.item_count_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        TextView txtCount = findViewById(R.id.txt_count);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EditText etxtItem = findViewById(R.id.etxt_new_item);
        Button button = findViewById(R.id.btn_add);

        mViewmodel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etxtItem.getText().toString();
                String amount = spinner.getSelectedItem().toString();
                DbTable dbTable = new DbTable();
                dbTable.item = name;
                dbTable.quantity = Integer.valueOf(amount);
                mViewmodel.insertNewItem(dbTable);
                etxtItem.getText().clear();
            }
        });


        List<DbTable> dbTables = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(dbTables);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        mViewmodel.getItems().observe(this, new Observer<List<DbTable>>() {
            @Override
            public void onChanged(List<DbTable> dbTables) {
                itemAdapter.setData(dbTables);
                itemAdapter.notifyDataSetChanged();
            }
        });

        mViewmodel.getItemCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                txtCount.setText(String.valueOf(integer));
            }
        });


    }
}
