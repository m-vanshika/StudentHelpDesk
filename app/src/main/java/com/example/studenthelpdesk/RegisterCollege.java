package com.example.studenthelpdesk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterCollege extends AppCompatActivity {
    //this instance will be referred to untill registration is completed
    static CollegeRegistrationData allData;
    EditText uname,cName,password,password2,adminmail;
    FirebaseFirestore firebaseFirestores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_college);
        allData=new CollegeRegistrationData();
        uname=findViewById(R.id.uname);
        cName=findViewById(R.id.cname);
        adminmail=findViewById(R.id.adminemail);
        password=findViewById(R.id.password2);
        password2=findViewById(R.id.confirmpassword2);
        firebaseFirestores=FirebaseFirestore.getInstance();

    }
    //move to next step of registration
    public void next(View v)
    {
        if(checkConstraints())
        {
            //intent to college registration step 2
            startActivity(new Intent(RegisterCollege.this, RegisterCollege2.class));
            finish();
        }
    }
    public void toLogin(View v)
    {
        //Intent to login
    }

    public Boolean checkConstraints()
    {
        final Boolean[] flag = {true};
        //everything not empty
        //check if valid mail
        //check if passwords match
        //check if password length greater than 8
        //check if username already exists
        DocumentReference documentReference = firebaseFirestores.collection("CollegeData").document(uname.getText().toString());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                //if username exist... a document will already be present in its name
                if(documentSnapshot.exists())
                {
                    uname.setError("This username already Exist");
                    flag[0] =false;
                }
            }
        });
        return flag[0];
    }
}