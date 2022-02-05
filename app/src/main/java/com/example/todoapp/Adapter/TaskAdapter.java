package com.example.todoapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Model.Task;
import com.example.todoapp.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.txtTaskID.setText("Task ID :" + taskList.get(position).getId());
        holder.txtTaskName.setText("Task Name :" + taskList.get(position).getTaskName());
        holder.txtTaskDescrption.setText("Task Description :" + taskList.get(position).getTaskDescription());
        holder.rLL.setOnClickListener(view -> {
            listener.onItemClick(taskList.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTaskID, txtTaskName, txtTaskDescrption;
        public RelativeLayout rLL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.txtTaskID = itemView.findViewById(R.id.txtTaskID);
            this.txtTaskName = itemView.findViewById(R.id.txtTaskName);
            this.txtTaskDescrption = itemView.findViewById(R.id.txtTaskDescrption);
            this.rLL = itemView.findViewById(R.id.rLL);
        }
    }

    //Declarative interface
    private ItemClickListener listener;

    //set method
    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    //Defining interface
    public interface ItemClickListener {
        //Achieve the click method, passing the subscript.
        void onItemClick(Task task);
    }
}


