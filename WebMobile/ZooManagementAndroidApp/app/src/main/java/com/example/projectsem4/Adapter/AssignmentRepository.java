package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.AssignmentCage;
import com.example.projectsem4.Entities.JobsForEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AssignmentRepository {

    @GET("/getCages/{code}")
    Call<List<AssignmentCage>> getCages(@Path("code") String code);

}
