package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.JobsForEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JobsforemployeeRepository {

    @GET("/getTimeTable/{code}")
    Call<List<JobsForEmployee>> findTimeTable(@Path("code") String code);

}
