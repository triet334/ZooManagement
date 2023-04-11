package com.example.projectsem4;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectsem4.Adapter.AnimalRepository;
import com.example.projectsem4.Adapter.AssignmentRepository;
import com.example.projectsem4.Adapter.HealthtrackingRepository;
import com.example.projectsem4.Entities.Animal;
import com.example.projectsem4.Entities.AssignmentCage;
import com.example.projectsem4.Entities.Cage;
import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Utility.UtilAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthTrackingActivity extends AppCompatActivity{

    Spinner spCage, spAnimal;
    //RadioButton rdHealth, rdHealth2;
    EditText txtEmployeeDescription;
    Button btnReportHealthTracking;
    AssignmentRepository assignmentRepository;
    AnimalRepository animalRepository;
    HealthtrackingRepository healthService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tracking);

        spCage = findViewById(R.id.spCage);
        spAnimal = findViewById(R.id.spAnimal);
//        rdHealth = findViewById(R.id.rdHealth);
//        rdHealth2 = findViewById(R.id.rdHealth2);
        txtEmployeeDescription = findViewById(R.id.txtEmployeeDescription);
        btnReportHealthTracking = findViewById(R.id.btnReportHealthTracking);

        assignmentRepository = UtilAPI.getAssignmentService();
        animalRepository = UtilAPI.getAnimalService();
        healthService = UtilAPI.getHealthService();

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");

        getCages(code);

    }

    private void getCages(String code) {
        assignmentRepository.getCages(code).enqueue(new Callback<List<AssignmentCage>>() {
            @Override
            public void onResponse(Call<List<AssignmentCage>> call, Response<List<AssignmentCage>> response) {
                List<AssignmentCage> list = response.body();

                ArrayAdapter<AssignmentCage> adapter = new ArrayAdapter<>(HealthTrackingActivity.this, android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                spCage.setAdapter(adapter);

                spCage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        AssignmentCage ass = (AssignmentCage) parent.getItemAtPosition(position);
                        int cageCode = ass.getCageCode().getCageCode();
                        if(cageCode >0){

                            animalRepository.getAnimals(cageCode).enqueue(new Callback<List<Animal>>() {
                                @Override
                                public void onResponse(Call<List<Animal>> call, Response<List<Animal>> response) {
                                    List<Animal> list = response.body();

                                    ArrayAdapter<Animal> adapter = new ArrayAdapter<>(HealthTrackingActivity.this, android.R.layout.simple_spinner_item,list);
                                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                                    spAnimal.setAdapter(adapter);

                                    spAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            Animal animal = (Animal) parent.getItemAtPosition(position);
                                            String animalCode = animal.getAnimalCode();
                                            if(animalCode != null){
                                                //dang lam
                                                btnReportHealthTracking.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        HealthTracking healthre = new HealthTracking();

//                                                        Cage cagere = new Cage();
//                                                        cagere.setCageCode(cageCode);
//                                                        healthre.setCageCode(cagere);

                                                        Animal animalre = new Animal();
                                                        animalre.setAnimalCode(animalCode);
                                                        healthre.setAnimalCode(animalre);

                                                        healthre.setEmployeeDescription(txtEmployeeDescription.getText().toString());

                                                        Employee empre = new Employee();
                                                        empre.setEmployeeCode(code);
                                                        healthre.setEmployeeCode(empre);

                                                        createHealthtracking(healthre);
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<List<Animal>> call, Throwable t) {
                                    Log.d("HealthTrackingActivity", t.getMessage());
                                }
                            });

                        }
                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

            }

            @Override
            public void onFailure(Call<List<AssignmentCage>> call, Throwable t) {
                Log.d("HealthTrackingActivity", t.getMessage());
            }
        });

    }

    private void createHealthtracking(HealthTracking health){
        Call<HealthTracking> call = healthService.createHealthtracking(health);
        call.enqueue(new Callback<HealthTracking>() {
            @Override
            public void onResponse(Call<HealthTracking> call, Response<HealthTracking> response) {
                if(response.isSuccessful()){
                    Toast.makeText(HealthTrackingActivity.this,"Create Success...",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HealthTrackingActivity.this, ActionActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(HealthTrackingActivity.this,"Fail !!!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<HealthTracking> call, Throwable t) {
                Log.d("Fail to connect to server !!!", t.getMessage());
            }
        });
    }
}