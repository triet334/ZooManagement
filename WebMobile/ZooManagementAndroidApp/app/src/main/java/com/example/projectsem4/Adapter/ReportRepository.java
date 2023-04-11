package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Entities.Report;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReportRepository {

    @GET("/getReport/{code}/{date}")
    Call<List<Report>>getReport(@Path("code") String code, @Path("date") String date);

    @PUT("/editReport/{name}")
    Call<Boolean> editReport(@Path("name") String name, @Body Report pr);

}
