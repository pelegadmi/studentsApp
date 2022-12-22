package com.example.class3demo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class StudentRecyclerList extends AppCompatActivity {
    List<Student> data;
    StudentRecyclerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        data = Model.instance().getAllStudents();
        RecyclerView list = findViewById(R.id.studentrecycler_list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(this));

        //initialing adapter
        adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        //add button
        FloatingActionButton addBtn = findViewById(R.id.student_list_add_button);
        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        adapter.notifyDataSetChanged();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = (int) cb.getTag();
                    Student st = data.get(pos);
                    st.setCb(cb.isChecked());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    listener.onItemClick(pos);
                }
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.getName());
            idTv.setText(st.getId());
            cb.setChecked(st.getCb());
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    public class rowClickedListener implements OnItemClickListener {
        public void onItemClick(int pos) {
            Intent intent = new Intent(StudentRecyclerList.this, StudentDetails.class);
            Bundle b = new Bundle();
            b.putInt("pos", pos);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        OnItemClickListener listener;


        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            setOnItemClickListener(new rowClickedListener());
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}