package com.example.projectsem4;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectsem4.Utility.UtilAPI;
import com.example.projectsem4.Adapter.EmployeeRepository;
import com.example.projectsem4.Entities.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EmployeeRepository repository;
    Button btnLoginx;
    EditText txtCodex,txtPassx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Login");

        repository = UtilAPI.getEmployeeService();
        btnLoginx = findViewById(R.id.btnLogin);
        btnLoginx.setOnClickListener(view -> {
            txtCodex=findViewById(R.id.txtCode);
            txtPassx=findViewById(R.id.txtPass);
            String code = txtCodex.getText().toString();
            String pass = txtPassx.getText().toString();
            checkLogin(code,pass);
        });
    }

    private void checkLogin(String code,String pass){
        Call<Employee> call = repository.checkLogin(code,pass);
        call.enqueue(new Callback<Employee>() {

            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Employee em = response.body();
                if (em!=null) {
                    SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("CODE",em.getEmployeeCode());
                    editor.putString("NAME",em.getEmployeeName());
                    editor.putString("POSITION",String.valueOf(em.getPositionCode().getPositionCode()));
                    editor.putString("CLASS",String.valueOf(em.getClassCode().getClassCode()));
                    editor.putString("IMG",em.getPhoto());

                    editor.commit();
                    if (em.getPositionCode().getPositionCode()==2){
                        Toast.makeText(MainActivity.this, "Employee Login Success!!!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,ActionActivity.class);
                        startActivity(intent);
                    }
                    else if (em.getPositionCode().getPositionCode()==3){
                        Toast.makeText(MainActivity.this, "Doctor Login Success!!!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,DoctorActivity.class);
                        startActivity(intent);
                    }
                    else if (em.getPositionCode().getPositionCode()==1){
                        Toast.makeText(MainActivity.this, "Login fail !!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Login fail !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(MainActivity.this, "Login fail !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}