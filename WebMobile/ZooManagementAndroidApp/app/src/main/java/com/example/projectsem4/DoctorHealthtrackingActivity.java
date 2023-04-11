package com.example.projectsem4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectsem4.Adapter.HealthtrackingAdapter;
import com.example.projectsem4.Adapter.HealthtrackingRepository;
import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Utility.UtilAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorHealthtrackingActivity extends AppCompatActivity {

    //ListView listViewHealth;
    RecyclerView recycler_view;
    HealthtrackingRepository healthRepository;
    List<HealthTracking> list = new ArrayList<>();
    HealthtrackingAdapter healthAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_healthtracking);

        recycler_view = findViewById(R.id.recycler_view);
        healthAdapter = new HealthtrackingAdapter(list, this);

        healthRepository = UtilAPI.getHealthService();
        recycler_view.setAdapter(healthAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");

        findHealthtrackingByCode(code);

        healthAdapter.setOnItemClickListener(new HealthtrackingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Lấy thông tin của item được chọn tại vị trí "position"
                HealthTracking health = list.get(position);
                String healhId = health.getHealthTrackingId().toString();
                if(healhId != null){
                    Intent intent = new Intent(DoctorHealthtrackingActivity.this, UpdateHealthtrackingActivity.class);
                    intent.putExtra("healthId", healhId);
                    startActivity(intent);
                }
            }
        });

    }


    private void findHealthtrackingByCode(String code) {
        healthRepository.getHealthtracking(code).enqueue(new Callback<List<HealthTracking>>() {
            @Override
            public void onResponse(Call<List<HealthTracking>> call, Response<List<HealthTracking>> response) {
                list.clear();
                //addAll tra ve 1 collection
                list.addAll(response.body());
                healthAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<HealthTracking>> call, Throwable t) {
                Log.d("DoctorHealthtrackingActivity", t.getMessage());
            }
        });

    }
}