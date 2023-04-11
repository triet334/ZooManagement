package com.example.projectsem4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectsem4.Entities.HealthTracking;
import com.example.projectsem4.R;

import java.util.List;

public class HealthtrackingAdapter extends RecyclerView.Adapter<HealthtrackingAdapter.ViewHolder> {

    List<HealthTracking> list;
    Context context;
    OnItemClickListener listener;

    public HealthtrackingAdapter(List<HealthTracking> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // định nghĩa phương thức setOnItemClickListener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // định nghĩa interface OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public HealthTracking getItem(int i) {
//        return list.get(i);
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reportemergency, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;

    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHealthTrackingId2,tvCage2,tvAnimal2,tvHealtStatus2,tvEmployeeDiscription2,tvEmployeeCode2;
        Button btnEditHealth;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Ánh xạ các thành phần
             tvHealthTrackingId2 = itemView.findViewById(R.id.tvHealthTrackingId2);
             tvCage2 = itemView.findViewById(R.id.tvCage2);
             tvAnimal2 = itemView.findViewById(R.id.tvAnimal2);
             tvHealtStatus2 = itemView.findViewById(R.id.tvHealtStatus2);
             tvEmployeeDiscription2 = itemView.findViewById(R.id.tvEmployeeDiscription2);
             tvEmployeeCode2 = itemView.findViewById(R.id.tvEmployeeCode2);
             btnEditHealth = itemView.findViewById(R.id.btnEditHealth);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthTracking heal = list.get(position);

        holder.tvHealthTrackingId2.setText(String.valueOf(heal.getHealthTrackingId()));
        //holder.tvCage2.setText(heal.getCageCode().getCageName());
        holder.tvAnimal2.setText(heal.getAnimalCode().getAnimalName());
        holder.tvHealtStatus2.setText(heal.getHealthStatus().toString());
        holder.tvEmployeeDiscription2.setText(heal.getEmployeeDescription());
        holder.tvEmployeeCode2.setText(heal.getEmployeeCode().getEmployeeName());

        holder.btnEditHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onItemClick(v, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null){
//            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            view= inflater.inflate(R.layout.reportemergency,null);
//        }
//
//        TextView tvHealthTrackingId2 = view.findViewById(R.id.tvHealthTrackingId2);
//        TextView tvCage2 = view.findViewById(R.id.tvCage2);
//        TextView tvAnimal2 = view.findViewById(R.id.tvAnimal2);
//        TextView tvHealtStatus2 = view.findViewById(R.id.tvHealtStatus2);
//        TextView tvEmployeeDiscription2 = view.findViewById(R.id.tvEmployeeDiscription2);
//        TextView tvEmployeeCode2 = view.findViewById(R.id.tvEmployeeCode2);
//        Button btnEditHealth = view.findViewById(R.id.btnEditHealth);
//
//        HealthTracking heal = getItem(i);
//        tvHealthTrackingId2.setText(String.valueOf(heal.getHealthTrackingId()));
//        tvCage2.setText(heal.getCageCode().getCageName());
//        tvAnimal2.setText(heal.getAnimalCode().getAnimalName());
//        tvHealtStatus2.setText(heal.getHealthStatus().toString());
//        tvEmployeeDiscription2.setText(heal.getEmployeeDescription());
//        tvEmployeeCode2.setText(heal.getEmployeeCode().getEmployeeName());
//
//        btnEditHealth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(listener != null){
//                    listener.onItemClick(,v,i,0);
//                }
//            }
//        });
//
//        return view;
//    }
}
