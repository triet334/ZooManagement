package com.example.projectsem4.Adapter;

import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.JobsForEmployee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeRepository {

    @POST("/login/{code}/{password}")
    Call<Employee> checkLogin(@Path("code") String code,@Path("password") String password);

    @GET("/findByCode/{code}")
    Call<Employee> findByCode(@Path("code") String code);

    @PUT("/editEmployee/{code}")
    Call<Boolean> editEmployee(@Path("code") String code, @Body Employee editEmp);

}
