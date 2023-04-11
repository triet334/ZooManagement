package com.example.projectsem4.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectsem4.Entities.Cage;
import com.example.projectsem4.Entities.JobsForEmployee;
import com.example.projectsem4.Entities.Report;
import com.example.projectsem4.MainActivity;
import com.example.projectsem4.R;
import com.example.projectsem4.ShowReportActivity;
import com.example.projectsem4.UpdateActivity;
import com.example.projectsem4.Utility.UtilAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportAdapter extends BaseAdapter {
    List<Report> list;
    Context context;

    Activity activity;
    PopupWindow popupWindow;

    ReportRepository reportRepository;


    public ReportAdapter(List<Report> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Report getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    //tao custom view
    public static class CustomView{
        TextView tvCage4;
        Button btnUpdateReport;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        CustomView customView;
        if (v == null){
            customView = new CustomView();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loadreport,null);
            customView.tvCage4 = v.findViewById(R.id.tvCage4);
            customView.btnUpdateReport = v.findViewById(R.id.btnshowRp);
            v.setTag(customView);
        }
        else{
            customView = (CustomView) view.getTag();
        }
//        set cho customView

        final int newPosition1 = i;
        customView.tvCage4.setText(list.get(i).getCageCode().getCageName());


        //chuyen toi vi tri moi

        customView.btnUpdateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(newPosition1);
            }
        });

        return v;
    }

    private void update(final int newPosition1){
        //create popup
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.editpopreport, activity.findViewById(R.id.popupWindow));

        //dinh nghia popupwindow va thiet lap size cho popupWindow nam giua
        popupWindow = new PopupWindow(layout, 800, 800, true);
        popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

        //get details
        final CheckBox cbCheckCage = layout.findViewById(R.id.cbxCheckCage);
        final CheckBox cbF = layout.findViewById(R.id.cbxFeeding);
        final CheckBox cbClean = layout.findViewById(R.id.cbxClean);
        //set vo
        cbCheckCage.setChecked(list.get(newPosition1).isCheckCage());
        cbF.setChecked(list.get(newPosition1).isFeeding());
        cbClean.setChecked(list.get(newPosition1).isClean());
        //xu ly update
        Button btnSend = layout.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Report report = new Report();
                report.setReportId(list.get(newPosition1).getReportId());
                Cage cage = new Cage();
                cage.setCageCode(list.get(newPosition1).getCageCode().getCageCode());
                report.setCageCode(cage);

                report.setCheckCage(cbCheckCage.isChecked());
                report.setClean(cbClean.isChecked());
                report.setFeeding(cbF.isChecked());
                report.setDate(list.get(newPosition1).getDate());

                reportRepository = UtilAPI.getReportService();


                SharedPreferences sharedPreferences = activity.getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                String name = sharedPreferences.getString("NAME","");

                Call<Boolean> call = reportRepository.editReport(name,report);
                call.enqueue(new Callback<Boolean>() {

                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Boolean em = response.body();

                        if (em) {
                            Toast.makeText(context, "Success edit information.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Edit fail !!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Log.d("Error!!!",t.getMessage());
                        Toast.makeText(context, "Can not connect to database !!!", Toast.LENGTH_SHORT).show();
                    }
                });

                //cap nhat lai, lam moi lai du lieu
                notifyDataSetChanged();

                //dong popup window
                popupWindow.dismiss();
            }
        });

    }
}
