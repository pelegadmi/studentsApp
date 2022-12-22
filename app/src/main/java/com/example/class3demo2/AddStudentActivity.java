package com.example.class3demo2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class3demo2.model.Student;
import com.example.class3demo2.model.Model;

public class AddStudentActivity extends AppCompatActivity {

    StudentRecyclerList.StudentRecyclerAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        EditText nameEt = findViewById(R.id.addstudent_name);
        EditText idEt = findViewById(R.id.addstudent_id);
        EditText phoneEt = findViewById(R.id.addstudent_phone);
        EditText addressEt = findViewById(R.id.addstudent_address);
        CheckBox checkEt = findViewById(R.id.studentDetails_check_dispaly);

        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button cancelBtn = findViewById(R.id.addstudent_cancel_btn);

        saveBtn.setOnClickListener(view -> {

            Model.instance().addStudent(new Student(
                    nameEt.getText().toString(),
                    idEt.getText().toString(),
                    phoneEt.getText().toString(),
                    addressEt.getText().toString(),
                    "",
                    checkEt.isChecked()));
            finish();
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}