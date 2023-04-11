package com.example.projectsem4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.projectsem4.Adapter.EmployeeRepository;
import com.example.projectsem4.Entities.ClassAnimal;
import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.EmployeePosition;
import com.example.projectsem4.Utility.UtilAPI;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    EditText txtCode2, txtName2, txtDoB2, txtAddress2, txtPhone2, txtEmail2, txtPass2;
    RadioButton rdMale2, rdFemale2;
    Button btnSave2, btnBack2;

    EmployeeRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initControls();

        repository = UtilAPI.getEmployeeService();

        btnBack2.setOnClickListener(v -> {
            Intent intent = new Intent(UpdateActivity.this, ActionActivity.class);
            startActivity(intent);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String code = sharedPreferences.getString("CODE","");

        int position = Integer.parseInt(sharedPreferences.getString("POSITION",""));
        int classAnimal = Integer.parseInt(sharedPreferences.getString("CLASS",""));

        findByCode(code);

        btnSave2.setOnClickListener(v -> {
            Employee emp = new Employee();
            emp.setEmployeeCode(txtCode2.getText().toString());
            emp.setEmployeeName(txtName2.getText().toString());
            emp.setBirthday(txtDoB2.getText().toString());
            emp.setAddress(txtAddress2.getText().toString());
            emp.setPhoneNumber(txtPhone2.getText().toString());
            emp.setEmail(txtEmail2.getText().toString());
            emp.setStatus(true);
            emp.setPassword("123");

            EmployeePosition pst = new EmployeePosition();
            pst.setPositionCode(position);
            emp.setPositionCode(pst);

            ClassAnimal cla = new ClassAnimal();
            cla.setClassCode(classAnimal);
            emp.setClassCode(cla);

            if(rdMale2.isChecked()){
                emp.setGender(true);
            }
            else{
                emp.setGender(false);
            }

            editEmployee(code,emp);
        });
    }

    private void editEmployee(String code,Employee emp) {
        Call<Boolean> call = repository.editEmployee(code,emp);

        call.enqueue(new Callback<Boolean>() {

            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Boolean em = response.body();
                if (em) {
                    Toast.makeText(UpdateActivity.this, "Success edit information.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateActivity.this, "Edit fail !!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(UpdateActivity.this, "Can not connect to database !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findByCode(String ecode) {
        Call<Employee> call = repository.findByCode(ecode);
        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Employee em = response.body();
                txtCode2.setText(em.getEmployeeCode());
                txtName2.setText(em.getEmployeeName());
                txtDoB2.setText(em.getBirthday());
                txtAddress2.setText(em.getAddress());
                txtPhone2.setText(em.getPhoneNumber());
                txtEmail2.setText(em.getEmail());

                if (em.getGender() == true){
                    rdMale2.setChecked(true);
                }
                else{
                    rdFemale2.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(UpdateActivity.this, "Can not connect to database !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static String Encrypt(String keys) {
        //để lưu dữ liệu mã hóa
        byte[] diget = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            //chuyển định dạng dữ liệu về dạng UTF8
            //UTF8 là định dạng
            md.update(keys.getBytes("UTF8"));
            //digest() để mã hóa
            diget=md.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return DatatypeConverter.printBase64Binary(diget);
    }

    public void initControls(){
        txtCode2 = findViewById(R.id.txtCode2);
        txtName2 = findViewById(R.id.txtName2);
        txtDoB2 = findViewById(R.id.txtDoB2);
        txtAddress2 = findViewById(R.id.txtAddress2);
        txtPhone2 = findViewById(R.id.txtPhone2);
        txtEmail2 = findViewById(R.id.txtEmail2);
        txtPass2 = findViewById(R.id.txtPass2);

        rdMale2 = findViewById(R.id.rdMale2);
        rdFemale2 = findViewById(R.id.rdFemale2);

        btnSave2 = findViewById(R.id.btnSave2);
        btnBack2 = findViewById(R.id.btnBack2);
    }

}