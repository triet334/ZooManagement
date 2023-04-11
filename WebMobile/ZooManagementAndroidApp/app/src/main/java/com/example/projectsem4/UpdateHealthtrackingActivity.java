package com.example.projectsem4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsem4.Adapter.EmployeeRepository;
import com.example.projectsem4.Adapter.HealthtrackingRepository;
import com.example.projectsem4.Entities.Animal;
import com.example.projectsem4.Entities.Cage;
import com.example.projectsem4.Entities.ClassAnimal;
import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.EmployeePosition;
import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.Utility.UtilAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateHealthtrackingActivity extends AppCompatActivity {

    EditText tvAnimal3, tvCage3, tvEmployeeDiscription3, tvEmployeeCode3, tvHealthTrackingId3;
    EditText txtDoctorNote;
    CheckBox ckbxStatus;
    Button btnUpdateHealthtracking;

    HealthtrackingRepository healthRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_healthtracking);

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");
        String name = sharedPreferences.getString("NAME","");

        healthRepository = UtilAPI.getHealthService();
        initControls();

        Intent i = getIntent();
        String Hid  = i.getStringExtra("healthId");
        int healthId = Integer.parseInt(Hid);

        if(healthId>0){
            find1Health(healthId);
        }

        btnUpdateHealthtracking.setOnClickListener(v -> {
            HealthTracking healths = new HealthTracking();
            healths.setHealthTrackingId(healthId);

            Animal animal = new Animal();
            animal.setAnimalCode(tvAnimal3.getText().toString());
            healths.setAnimalCode(animal);

//            Cage cage = new Cage();
//            cage.setCageCode(Integer.parseInt(tvCage3.getText().toString()));
//            healths.setCageCode(cage);

            healths.setEmployeeDescription(tvEmployeeDiscription3.getText().toString());

            Employee employee = new Employee();
            employee.setEmployeeCode(tvEmployeeCode3.getText().toString());
            healths.setEmployeeCode(employee);

            if(ckbxStatus.isChecked()){
                healths.setHealthStatus(true);
            }
            else{
                healths.setHealthStatus(false);
            }

            healths.setDoctorNote(txtDoctorNote.getText().toString());

            Employee emp = new Employee();
            emp.setEmployeeCode(code);
            emp.setEmployeeName(name);
            healths.setDoctorCode(emp);

            editHealthtracking(healthId,healths);
        });

    }

    private void editHealthtracking(int id,HealthTracking healthTracking) {
        Call<Boolean> call = healthRepository.editHealthtracking(id,healthTracking);

        call.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean em = response.body();
                if (em) {
                    Toast.makeText(UpdateHealthtrackingActivity.this, "Success edit information.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateHealthtrackingActivity.this, DoctorActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateHealthtrackingActivity.this, "Edit fail !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(UpdateHealthtrackingActivity.this, "Can not connect to database !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void find1Health(int ecode) {
        Call<HealthTracking> call = healthRepository.find1Health(ecode);
        call.enqueue(new Callback<HealthTracking>() {
            @Override
            public void onResponse(Call<HealthTracking> call, Response<HealthTracking> response) {
                HealthTracking health = response.body();
                tvHealthTrackingId3.setText(health.getHealthTrackingId().toString());
                tvAnimal3.setText(health.getAnimalCode().getAnimalCode());
                //tvCage3.setText(String.valueOf(health.getCageCode().getCageCode()));
                tvEmployeeDiscription3.setText(health.getEmployeeDescription());
                tvEmployeeCode3.setText(health.getEmployeeCode().getEmployeeCode());
            }

            @Override
            public void onFailure(Call<HealthTracking> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(UpdateHealthtrackingActivity.this, "Can not connect to database !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void initControls(){
        tvHealthTrackingId3 = findViewById(R.id.tvHealthTrackingId3);
        tvAnimal3 = findViewById(R.id.tvAnimal3);
        tvCage3 = findViewById(R.id.tvCage3);
        tvEmployeeDiscription3 = findViewById(R.id.tvEmployeeDiscription3);
        tvEmployeeCode3 = findViewById(R.id.tvEmployeeCode3);

        txtDoctorNote = findViewById(R.id.txtDoctorNote);
        ckbxStatus = findViewById(R.id.ckbxStatus);
        btnUpdateHealthtracking = findViewById(R.id.btnUpdateHealthtracking);
    }

}