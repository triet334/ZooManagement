package com.example.projectsem4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.projectsem4.Adapter.JobsforemployeeAdapter;
import com.example.projectsem4.Adapter.JobsforemployeeRepository;
import com.example.projectsem4.Entities.JobsForEmployee;
import com.example.projectsem4.Utility.UtilAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyActivity extends AppCompatActivity {

    ListView listViewDaily;
    JobsforemployeeRepository dailyRepository;
    List<JobsForEmployee> list = new ArrayList<>();
    JobsforemployeeAdapter dailyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        listViewDaily = findViewById(R.id.listViewDaily);
        dailyAdapter = new JobsforemployeeAdapter(list, this);

        dailyRepository = UtilAPI.getJobsService();
        listViewDaily.setAdapter(dailyAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");

        findTimeTableByCode(code);
    }

    private void findTimeTableByCode(String code) {
        dailyRepository.findTimeTable(code).enqueue(new Callback<List<JobsForEmployee>>() {
            @Override
            public void onResponse(Call<List<JobsForEmployee>> call, Response<List<JobsForEmployee>> response) {
                list.clear();
                //addAll tra ve 1 collection
                list.addAll(response.body());
                dailyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<JobsForEmployee>> call, Throwable t) {
                Log.d("DailyActivity", t.getMessage());
            }
        });

    }
}