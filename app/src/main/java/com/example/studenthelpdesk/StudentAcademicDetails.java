package com.example.studenthelpdesk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StudentAcademicDetails extends AppCompatActivity {
    StudentData studentData;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_academic_details);
        ll = findViewById(R.id.ll);
        studentData = StudentPage.studentData;
        ArrayList<CollegeRegisterQuestions> quesAns = studentData.getAcademic_ques();
        for (CollegeRegisterQuestions a : quesAns) {
            View repeatAnswers = getLayoutInflater().inflate(R.layout.repeatable_student_details, null);
            TextView ques = repeatAnswers.findViewById(R.id.Ques);
            TextView ans = repeatAnswers.findViewById(R.id.ans);
            ques.setText(a.getQuestion());
            ans.setText(a.getAnswer());
            ll.addView(repeatAnswers);
            ques.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (a.isChangeable() == true) {
                        public void changeVal () {
                            AlertDialog.Builder builder = new AlertDialog.Builder(StudentAcademicDetails.this);
                            builder.setTitle(a.getQuestion());
                            builder.setMessage("New Value");
                            EditText neww = new EditText(this);
                            builder.setCancelable()
                                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ans = neww.getText();
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            //do nothing
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();

                        }
                    } else {
                        // Toast.makeText(this,"SEND REQUEST FOR CHANGE",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
