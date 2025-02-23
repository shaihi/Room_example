package com.shaihi.room_example;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDataBase dataBase;
    private StudentDao studentDao;

    EditText etStudentName, etStudentGrade;
    Button btnAdd, btnShow;
    TextView tvStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etStudentGrade = findViewById(R.id.etGrade);
        etStudentName = findViewById(R.id.etStudentName);
        btnAdd = findViewById(R.id.btnAdd);
        btnShow = findViewById(R.id.btnShow);
        tvStudentList = findViewById(R.id.tvStudentsList);

        // ✅ Initialize Room Database
        dataBase = AppDataBase.getInstance(this);
        studentDao = dataBase.studentDao(); // ✅ Get the DAO

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = etStudentName.getText().toString();
                int grade;
                try {
                    grade = Integer.parseInt(etStudentGrade.getText().toString());
                } catch (NumberFormatException e) {
                    Log.e("Room Example","Not an Integer format" + e.toString());
                    grade = -1;
                }

                studentDao.insertStudent(new Students(studentName, grade));
                etStudentGrade.setText("");
                etStudentName.setText("");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Students> students = studentDao.getAllStudents();
                StringBuilder result = new StringBuilder();
                for(Students student: students){
                    result.append(student.id).append(": ")
                            .append(student.name).append(" - ")
                            .append(student.grade).append("\n");
                }
                tvStudentList.setText(result.toString());
            }
        });
    }
}