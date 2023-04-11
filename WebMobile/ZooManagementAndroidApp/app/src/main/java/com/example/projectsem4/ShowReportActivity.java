package com.example.projectsem4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.projectsem4.Adapter.ReportAdapter;
import com.example.projectsem4.Adapter.ReportRepository;
import com.example.projectsem4.Entities.Report;
import com.example.projectsem4.Utility.UtilAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowReportActivity extends AppCompatActivity {

    ListView listViewReport;
    ReportRepository reportRepository;
    List<Report> list = new ArrayList<>();
    ReportAdapter reportAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_report);

        listViewReport = findViewById(R.id.listViewReport);
        reportAdapter = new ReportAdapter(list,this,this);

        reportRepository = UtilAPI.getReportService();
        listViewReport.setAdapter(reportAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");

        Intent intent = getIntent();
        String wdate = intent.getStringExtra("jb");

        findReport(code,wdate);
    }

    private void findReport (String code,String date){
        reportRepository.getReport(code,date).enqueue(new Callback<List<Report>>() {
            @Override
            public void onResponse(Call<List<Report>> call, Response<List<Report>> response) {
                list.clear();

                list.addAll(response.body());
                reportAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Report>> call, Throwable t) {
                Log.d("ShowReportActivity", t.getMessage());
            }
        });
    }
}