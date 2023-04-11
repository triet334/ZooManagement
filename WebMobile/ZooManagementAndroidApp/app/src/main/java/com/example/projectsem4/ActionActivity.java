package com.example.projectsem4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsem4.Adapter.EmployeeRepository;
import com.example.projectsem4.Utility.UtilAPI;
import com.example.projectsem4.Entities.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActionActivity extends AppCompatActivity {

    EmployeeRepository repository;
    TextView txtCode;
    ImageView imgPhoto;
    Button btnDaily, btnReport, btnGetHealthtracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);


        getSupportActionBar().setTitle("Action");

        repository = UtilAPI.getEmployeeService();

        //lấy tên
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("NAME","");
        txtCode = findViewById(R.id.txtName);
        txtCode.setText(name);

        //lấy hình
        String img = sharedPreferences.getString("IMG","");
        byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        imgPhoto = findViewById(R.id.imgPhoto);
        imgPhoto.setImageBitmap(decodedByte);

        //chuyển trang xem công việc
        btnDaily = findViewById(R.id.btnDaily);
        btnDaily.setOnClickListener(v -> {
            Intent intent = new Intent(ActionActivity.this, DailyActivity.class);
            startActivity(intent);
        });

        //chuyen den trang create healthTracking
        btnReport = findViewById(R.id.btnReport);
        btnReport.setOnClickListener(v -> {
            Intent intent = new Intent(ActionActivity.this, HealthTrackingActivity.class);
            startActivity(intent);
        });

        //chuyen den trang xem report cho doctor
//        btnGetHealthtracking = findViewById(R.id.btnGetHealthtracking);
//        btnGetHealthtracking.setOnClickListener(v -> {
//            Intent intent = new Intent(ActionActivity.this, DoctorHealthtrackingActivity.class);
//            startActivity(intent);
//        });

    }

    // gắn menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return true;
    }

    //xử lý
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.btnUpdate: intent = new Intent(ActionActivity.this,UpdateActivity.class);
                startActivity(intent);
                break;
            case R.id.btnLogOut: intent = new Intent(ActionActivity.this,LogoutActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findByCode(String ecode) {
        Call<Employee> call = repository.findByCode(ecode);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Employee em = response.body();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("Error!!!",t.getMessage());
                Toast.makeText(ActionActivity.this, "Bla  Bla!!!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}