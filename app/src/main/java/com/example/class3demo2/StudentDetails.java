package com.example.class3demo2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class3demo2.model.Model;
import com.example.class3demo2.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentDetails extends AppCompatActivity {
    int studentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            studentPos = b.getInt("pos");
          fillStudentDetails();

            Button editBtn = findViewById(R.id.studentDetails_edit_btn);
            editBtn.setOnClickListener(view -> {
                editClickListener();
            });
        }

    }

    private void editClickListener() {
        Intent intent = new Intent(this, EditStudent.class);
        Bundle bundle = new Bundle();
        bundle.putInt("studentToEdit", studentPos);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onRestart() {
        super.onRestart();
        fillStudentDetails();
    }

    private void fillStudentDetails() {
        try {
            Student st = Model.instance().getAllStudents().get(studentPos);

            TextView studentName = findViewById(R.id.studentDetails_name_display);
            TextView studentId = findViewById(R.id.studentDetails_id_display);
            TextView studentPhone = findViewById(R.id.studentDetails_phone_display);
            TextView studentAddress = findViewById(R.id.studentDetails_address_display);
            CheckBox studentCheckbox = findViewById(R.id.studentDetails_check_dispaly);


            studentName.setText(st.getName());
            studentId.setText(st.getId());
            studentPhone.setText(st.getPhone());
            studentAddress.setText(st.getAddress());
            studentCheckbox.setChecked(st.getCb());

        } catch (Exception e) {
            finish();
        }
    }


}