package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<com.example.myapplication.Task> tasks = new ArrayList<>();
    Context context;
    public TaskAdapter(ArrayList<Task> tasks , Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        Task task;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_fragment,parent,false);
        TaskViewHolder taskViewHolder=new TaskViewHolder(view);
        return taskViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.task = tasks.get(position);
        TextView title = holder.itemView.findViewById(R.id.fragment_title_text);
        TextView body = holder.itemView.findViewById(R.id.fragment_body_text);
        TextView state= holder.itemView.findViewById(R.id.fragment_state_text);

        title.setText(holder.task.getTitle());
        body.setText(holder.task.getBody());
        state.setText(holder.task.getState());

        holder.itemView.setOnClickListener(v -> {
            Intent taskDetailsIntent = new Intent(context, DetailsTask.class); //v.getContext, TaskDetailActivity.class
            taskDetailsIntent.putExtra("taskName",holder.task.getTitle());
            context.startActivity(taskDetailsIntent);// v.getContext.startActivity(taskDetailsIntent)
        });
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
