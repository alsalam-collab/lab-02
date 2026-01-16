package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedPosition = -1; // To track which item to delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnDelete = findViewById(R.id.btnDelete);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>(Arrays.asList(cities));

        // Use the content layout provided in the files
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Track clicks to select a city for deletion
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
        });

        // ADD CITY logic
        btnAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New City");
            final EditText input = new EditText(this);
            builder.setView(input);

            builder.setPositiveButton("CONFIRM", (dialog, which) -> {
                String cityName = input.getText().toString();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged(); // Refresh UI
                }
            });
            builder.setNegativeButton("CANCEL", null);
            builder.show();
        });

        // DELETE CITY logic
        btnDelete.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                dataList.remove(selectedPosition);
                cityAdapter.notifyDataSetChanged(); // Refresh UI
                selectedPosition = -1; // Reset selection
            }
        });
    }
}