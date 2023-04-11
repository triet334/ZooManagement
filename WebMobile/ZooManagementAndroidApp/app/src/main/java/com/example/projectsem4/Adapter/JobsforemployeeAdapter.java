package com.example.projectsem4.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectsem4.Entities.Employee;
import com.example.projectsem4.Entities.JobsForEmployee;
import com.example.projectsem4.R;
import com.example.projectsem4.ShowReportActivity;

import java.util.List;

public class JobsforemployeeAdapter extends BaseAdapter {
    List<JobsForEmployee> list;
    Context context;

    public JobsforemployeeAdapter(List<JobsForEmployee> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public JobsForEmployee getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.timetable,null);
        }

        //TextView tvFeedId = view.findViewById(R.id.tvFeedId);
        TextView tvShift = view.findViewById(R.id.tvShift);
        TextView tvWorkDate = view.findViewById(R.id.tvWorkDate);
        Button btnshowReport = view.findViewById(R.id.btnshowReport);

        JobsForEmployee jobs = getItem(i);
        //tvFeedId.setText(Integer.toString(jobs.getFeedTableId()));
        //tvCode.setText(jobs.getEmployeeCode().getEmployeeCode());
        tvShift.setText(jobs.getShiftCode().getShiftTime());
        tvWorkDate.setText(jobs.getWorkDate());

        btnshowReport.setOnClickListener(v -> {
            String wdate =getItem(i).getWorkDate();
            Intent intent=new Intent(context, ShowReportActivity.class);
            intent.putExtra("jb",wdate);
            context.startActivity(intent);
        });

//        btnShowRp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String wdate =getItem(i).getWorkDate();
//                Intent intent=new Intent(context, ShowReportActivity.class);
//                intent.putExtra("jb",wdate);
//                context.startActivity(intent);
//            }
//        });

        return view;
    }
}
