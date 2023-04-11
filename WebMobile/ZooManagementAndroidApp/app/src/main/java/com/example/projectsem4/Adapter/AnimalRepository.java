package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.Animal;
import com.example.projectsem4.Entities.AssignmentCage;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnimalRepository {
    @GET("/getAnimals/{code}")
    Call<List<Animal>> getAnimals(@Path("code") int code);
}
