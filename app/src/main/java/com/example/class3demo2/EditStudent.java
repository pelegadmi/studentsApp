package com.example.class3demo2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;

public class EditStudent extends AppCompatActivity {
    int studentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            studentPos = b.getInt("studentToEdit");
            Student st = Model.instance().getAllStudents().get(studentPos);

            EditText studentName = findViewById(R.id.studentEdit_name);
            EditText studentId = findViewById(R.id.studentEdit_id);
            EditText studentPhone = findViewById(R.id.studentEdit_phone);
            EditText studentAddress = findViewById(R.id.studentEdit_address);
            CheckBox studentCheckbox = findViewById(R.id.studentEdit_check);

            studentName.setText(st.getName());
            studentId.setText(st.getId());
            studentPhone.setText(st.getPhone());
            studentAddress.setText(st.getAddress());
            studentCheckbox.setChecked(st.getCb());

            Button saveBtn = findViewById(R.id.studentEdit_save_btn);
            Button deleteBtn = findViewById(R.id.studentEdit_delete_btn);
            Button cancelBtn = findViewById(R.id.studentEdit_cancel_btn);
            saveBtn.setOnClickListener(view -> {
                Student editedStudent = new Student(
                        studentName.getText().toString(),
                        studentId.getText().toString(),
                        studentPhone.getText().toString(),
                        studentAddress.getText().toString(),
                        "",
                        studentCheckbox.isChecked());
                Model.instance().updateStudent(editedStudent, studentPos);
                finish();
            });

            deleteBtn.setOnClickListener(view -> {
                        Model.instance().deleteStudent(studentPos);
                        Intent intent = new Intent(this, StudentRecyclerList.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
            );

            cancelBtn.setOnClickListener(view -> finish());
        }
    }
}