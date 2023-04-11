package com.example.projectsem4.Utility;

import com.example.projectsem4.Adapter.AnimalRepository;
import com.example.projectsem4.Adapter.AssignmentRepository;
import com.example.projectsem4.Adapter.EmployeeRepository;
import com.example.projectsem4.Adapter.HealthtrackingRepository;
import com.example.projectsem4.Adapter.JobsforemployeeRepository;
import com.example.projectsem4.Adapter.ReportRepository;
import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Entities.JobsForEmployee;

public class UtilAPI {
    private static final String url="http:/172.16.3.130:9999/";

    //định nghĩa
    public static EmployeeRepository getEmployeeService(){
        return RetrofitClient.getClient(url).create(EmployeeRepository.class);
    }

    public static JobsforemployeeRepository getJobsService(){
        return RetrofitClient.getClient(url).create(JobsforemployeeRepository.class);
    }

    public static AssignmentRepository getAssignmentService(){
        return RetrofitClient.getClient(url).create(AssignmentRepository.class);
    }

    public static AnimalRepository getAnimalService(){
        return RetrofitClient.getClient(url).create(AnimalRepository.class);
    }

    public static HealthtrackingRepository getHealthService(){
        return RetrofitClient.getClient(url).create(HealthtrackingRepository.class);
    }

    public static ReportRepository getReportService(){
        return RetrofitClient.getClient(url).create(ReportRepository.class);
    }

}
