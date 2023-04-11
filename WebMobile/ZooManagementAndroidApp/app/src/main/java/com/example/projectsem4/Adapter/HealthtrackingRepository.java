package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Entities.JobsForEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HealthtrackingRepository {

    @POST("/createHealthtracking")
    Call<HealthTracking> createHealthtracking(@Body HealthTracking health);

    @GET("/getHealthtracking/{code}")
    Call<List<HealthTracking>> getHealthtracking(@Path("code") String code);

    @GET("/find1Health/{id}")
    Call<HealthTracking> find1Health(@Path("id") int id);

    @PUT("/editHealthtracking/{id}")
    Call<Boolean> editHealthtracking(@Path("id") int id, @Body HealthTracking health);
}
